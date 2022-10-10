package com.github.ants280.jeff.farm.ws.dao.api.crud;

import com.github.ants280.jeff.farm.ws.dao.UserIdDao;
import com.github.ants280.jeff.farm.ws.dao.api.SqlFunctionDao;
import com.github.ants280.jeff.farm.ws.dao.api.call.SimpleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.ListResultSetTransformer;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.SimpleResultSetTransformer;
import com.github.ants280.jeff.farm.ws.model.CrudItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

public abstract class CrudItemDao<T extends CrudItem<T>> extends SqlFunctionDao
{
	protected CrudItemDao(DataSource dataSource, UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
	}

	public abstract int create(T entity);

	public abstract T read(int id);

	public abstract List<T> readList(int parentId);

	public abstract void update(T entity);

	public abstract void delete(int id);

	public abstract boolean canDelete(int id);

	protected abstract T mapRow(ResultSet rs) throws SQLException;

	protected int executeCreate(
		String functionName, List<SqlFunctionParameter<?>> inParameters, String idColumn)
	{
		SqlFunctionCall<Integer>
			functionCall
			= new SimpleCommandSqlFunctionCall<>(functionName,
			inParameters,
			new SimpleResultSetTransformer<>(resultSet -> resultSet.getInt(idColumn)),
			userIdDao);
		return this.execute(functionCall);
	}

	protected T executeRead(
		String functionName, List<SqlFunctionParameter<?>> inParameters)
	{
		SqlFunctionCall<T> functionCall = new SimpleCommandSqlFunctionCall<>(
			functionName,
			inParameters,
			new SimpleResultSetTransformer<>(this::mapRow),
			userIdDao);
		return this.execute(functionCall);
	}

	protected List<T> executeReadList(
		String functionName, List<SqlFunctionParameter<?>> inParameters)
	{
		SqlFunctionCall<List<T>>
			functionCall
			= new SimpleCommandSqlFunctionCall<>(functionName,
			inParameters,
			new ListResultSetTransformer<>(this::mapRow),
			userIdDao);
		return this.execute(functionCall);
	}

	protected void executeUpdate( // and delete
		String functionName, List<SqlFunctionParameter<?>> inParameters)
	{
		SqlFunctionCall<Void> functionCall = new SimpleCommandSqlFunctionCall<>(functionName,
			inParameters,
			null,
			userIdDao);
		this.execute(functionCall);
	}

	protected boolean canDelete(
		String functionName,
		List<SqlFunctionParameter<?>> inParameters,
		String outParameterName)
	{
		SqlFunctionCall<Boolean>
			functionCall
			= new SimpleCommandSqlFunctionCall<>(functionName,
			inParameters,
			new SimpleResultSetTransformer<>(resultSet -> resultSet.getBoolean(
				outParameterName)),
			userIdDao);
		return this.execute(functionCall);
	}
}
