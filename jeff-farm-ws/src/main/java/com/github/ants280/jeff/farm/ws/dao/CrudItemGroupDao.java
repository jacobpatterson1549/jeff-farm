package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.CrudItem;
import com.github.ants280.jeff.farm.ws.model.CrudItemGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;
import javax.sql.DataSource;

// TODO: is it possible to use better generics?  It is annoying to have to put the V param first...
public abstract class CrudItemGroupDao<V extends CrudItem, T extends CrudItemGroup<V, T>> extends SqlFunctionDao
{
	public CrudItemGroupDao(DataSource dataSource)
	{
		super(dataSource);
	}

	public abstract int create(T entity);

	public abstract T read(int id);

	public abstract List<T> readList(int parentId);

	public abstract void update(int id, T entity);

	public abstract void delete(int id);

	public abstract boolean canDelete(int id);

	protected abstract T mapGroup(ResultSet rs) throws SQLException;

	protected abstract V mapItem(ResultSet rs) throws SQLException;

	protected int executeCreate(
		String createGroupFunctionName,
		List<SqlFunctionParameter> groupInParameters,
		String createItemsFunctionName,
		List<List<SqlFunctionParameter>> itemInParameters,
		int userId)
	{
		SqlFunctionCall<Integer> groupFunctionCall = new SingleCommandSqlFunctionCall<>(
			createGroupFunctionName,
			groupInParameters,
			new SimpleResultSetTransformer<>(false, null));
		SqlFunctionCall<Integer> itemsFunctionCall = new BatchCommandSqlFunctionCall<>(
			createItemsFunctionName,
			itemInParameters);
		return this.executeSingle(userId, groupFunctionCall, itemsFunctionCall);
	}

	protected T executeRead(
		String functionName,
		List<SqlFunctionParameter> inParameters, Function<V, Integer> groupIdMappingFunction)
	{
		SqlFunctionCall<T> functionCall = new SingleCommandSqlFunctionCall<>(
			functionName,
			inParameters,
			new CrudItemGroupResultSetTransformer<>(
				true,
				this::mapGroup,
				this::mapItem,
		groupIdMappingFunction));
		return this.execute(null, functionCall).get(0);
	}

	protected List<T> executeReadList(
		String functionName,
		List<SqlFunctionParameter> inParameters, Function<V, Integer> groupIdMappingFunction)
	{
		SqlFunctionCall<T> functionCall = new SingleCommandSqlFunctionCall<>(
			functionName,
			inParameters,
			new CrudItemGroupResultSetTransformer<>(
				false,
				this::mapGroup,
				this::mapItem,
				groupIdMappingFunction));
		return this.execute(null, functionCall);
	}

	protected void executeUpdate(
		String updateGroupFunctionName,
		List<SqlFunctionParameter> groupInParameters,
		String updateItemsFunctionName,
		List<List<SqlFunctionParameter>> itemInParameters,
		int userId)
	{
		// TODO: Ensure all items in group have id of parent that is updated... maybe in implementations.
		// TODO: validate all itemInParameter lists are in same order...
		SqlFunctionCall<Void> groupFunctionCall = new SingleCommandSqlFunctionCall<>(
			updateGroupFunctionName,
			groupInParameters,
			new SimpleResultSetTransformer<>(false, null));
		SqlFunctionCall<Void> itemsFunctionCall = new BatchCommandSqlFunctionCall<>(
			updateItemsFunctionName,
			itemInParameters);
		this.executeUpdate(userId, groupFunctionCall, itemsFunctionCall);
	}


	protected void executeDelete(
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
		SqlFunctionCall<Boolean> functionCall = new SingleCommandSqlFunctionCall<>(
			functionName,
			inParameters,
			new SimpleResultSetTransformer<>(
				true,
				resultSet -> resultSet.getBoolean(outParameterName)));
		return this.executeSingle(null, functionCall);
	}
}
