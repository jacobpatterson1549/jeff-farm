package com.github.ants280.jeff.farm.ws.dao.api.crud;

import com.github.ants280.jeff.farm.ws.dao.api.SqlFunctionDao;
import com.github.ants280.jeff.farm.ws.dao.api.call.SingleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.SimpleResultSetTransformer;
import com.github.ants280.jeff.farm.ws.model.CrudItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

public abstract class CrudItemDao<T extends CrudItem> extends SqlFunctionDao
{
	public CrudItemDao(DataSource dataSource)
	{
		super(dataSource);
	}

	public abstract int create(T entity);

	public abstract T read(int id);

	public abstract List<T> readList(int parentId);

	public abstract void update(int id, T entity);

	public abstract void delete(int id);

	public abstract boolean canDelete(int id);

	protected abstract T mapRow(ResultSet rs) throws SQLException;

	protected int executeCreate(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		int userId)
	{
		SqlFunctionCall<Integer>
			functionCall
			= new SingleCommandSqlFunctionCall<>(functionName,
			inParameters,
			new SimpleResultSetTransformer<>(true,
				resultSet -> resultSet.getInt(CrudItem.ID_COLUMN)));
		return this.execute(userId, functionCall).get(0);
	}

	protected T executeRead(
		String functionName, List<SqlFunctionParameter> inParameters)
	{
		SqlFunctionCall<T> functionCall = new SingleCommandSqlFunctionCall<>(
			functionName,
			inParameters,
			new SimpleResultSetTransformer<>(true, this::mapRow));
		return this.executeSingle(null, functionCall);
	}

	protected List<T> executeReadList(
		String functionName, List<SqlFunctionParameter> inParameters)
	{
		SqlFunctionCall<T> functionCall = new SingleCommandSqlFunctionCall<>(
			functionName,
			inParameters,
			new SimpleResultSetTransformer<>(false, this::mapRow));
		return this.execute(null, functionCall);
	}

	protected void executeUpdate( // and delete
		String functionName,
		List<SqlFunctionParameter> inParameters,
		int userId)
	{
		SqlFunctionCall<Void> functionCall = new SingleCommandSqlFunctionCall<>(
			functionName,
			inParameters,
			new SimpleResultSetTransformer<>(false, null));
		this.executeUpdate(userId, functionCall);
	}

	protected boolean canDelete(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		String outParameterName)
	{
		SqlFunctionCall<Boolean>
			functionCall
			= new SingleCommandSqlFunctionCall<>(functionName,
			inParameters,
			new SimpleResultSetTransformer<>(true,
				resultSet -> resultSet.getBoolean(outParameterName)));
		return this.executeSingle(null, functionCall);
	}
}
