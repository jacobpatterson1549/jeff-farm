package com.github.ants280.jeff.farm.ws.dao.api.crud;

import com.github.ants280.jeff.farm.ws.dao.UserIdDao;
import com.github.ants280.jeff.farm.ws.dao.api.SqlFunctionDao;
import com.github.ants280.jeff.farm.ws.dao.api.call.BatchCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SimpleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.ListResultSetTransformer;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.SimpleResultSetTransformer;
import com.github.ants280.jeff.farm.ws.model.CrudItemInspection;
import com.github.ants280.jeff.farm.ws.model.CrudItemInspectionGroup;
import com.github.ants280.jeff.farm.ws.model.CrudItemInspectionGroupUpdate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.sql.DataSource;

public abstract class CrudItemInspectionGroupDao<V extends CrudItemInspection<?, V>, T extends CrudItemInspectionGroup<V, T>>
	extends SqlFunctionDao
{
	public CrudItemInspectionGroupDao(DataSource dataSource, UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
	}

	public abstract int create(T entity);

	public abstract T read(int id);

	public abstract List<T> readList(int parentId);

	public abstract void update(
		int id, CrudItemInspectionGroupUpdate<V, T> entityUpdate);

	public abstract void delete(int id);

	public abstract boolean canDelete(int id);

	public abstract Map<Integer, String> getTargets(int parentId);

	protected abstract T mapGroup(ResultSet rs) throws SQLException;

	protected abstract V mapItem(ResultSet rs) throws SQLException;

	protected int executeCreate(
		String createGroupFunctionName,
		List<SqlFunctionParameter> groupInParameters,
		String createItemsFunctionName,
		List<List<SqlFunctionParameter>> itemInParameters,
		String groupIdColumnName,
		String parentIdColumnName)
	{
		SqlFunctionCall<Integer>
			createGroupFunctionCall
			= new ParentIdSettingSqlFunctionCall(createGroupFunctionName,
			groupInParameters,
			itemInParameters,
			groupIdColumnName,
			parentIdColumnName,
			userIdDao);
		SqlFunctionCall<Void>
			createItemsFunctionCall
			= new BatchCommandSqlFunctionCall(createItemsFunctionName,
			itemInParameters,
			userIdDao);

		return this.execute((a, b) -> a,
			createGroupFunctionCall,
			createItemsFunctionCall);
	}

	protected T executeRead(
		String readGroupFunctionName,
		List<SqlFunctionParameter> readGroupInParameters,
		String readItemsFunctionName,
		List<SqlFunctionParameter> readItemsInParameters)
	{
		SqlFunctionCall<T>
			readGroupFunctionCall
			= new SimpleCommandSqlFunctionCall<>(readGroupFunctionName,
			readGroupInParameters,
			new SimpleResultSetTransformer<>(this::mapGroup),
			userIdDao);
		SqlFunctionCall<List<V>>
			readItemsFunctionCall
			= new SimpleCommandSqlFunctionCall<>(readItemsFunctionName,
			readItemsInParameters,
			new ListResultSetTransformer<>(this::mapItem),
			userIdDao);

		return this.execute(this::addItemsToGroup,
			readGroupFunctionCall,
			readItemsFunctionCall);
	}

	protected List<T> executeReadList(
		String readGroupFunctionName,
		List<SqlFunctionParameter> readGroupInParameters,
		String readItemsFunctionName,
		List<SqlFunctionParameter> readItemsInParameters)
	{
		SimpleCommandSqlFunctionCall<List<T>>
			readGroupFunctionCall
			= new SimpleCommandSqlFunctionCall<>(readGroupFunctionName,
			readGroupInParameters,
			new ListResultSetTransformer<>(this::mapGroup),
			userIdDao);
		SqlFunctionCall<List<V>>
			readItemsFunctionCall
			= new SimpleCommandSqlFunctionCall<>(readItemsFunctionName,
			readItemsInParameters,
			new ListResultSetTransformer<>(this::mapItem),
			userIdDao);

		return this.execute(this::partitionItemsForGroups,
			readGroupFunctionCall,
			readItemsFunctionCall);
	}

	protected void executeUpdate(
		String updateGroupFunctionName,
		List<SqlFunctionParameter> groupInParameters,
		String updateItemsFunctionName,
		List<List<SqlFunctionParameter>> itemInParameters,
		String createItemsFunctionName,
		List<List<SqlFunctionParameter>> createInParameters,
		String deleteItemsFunctionName,
		List<List<SqlFunctionParameter>> deleteItemsInParameters)
	{
		SqlFunctionCall<Void>
			updateGroupFunctionCall
			= new SimpleCommandSqlFunctionCall<>(updateGroupFunctionName,
			groupInParameters,
			null,
			userIdDao);
		SqlFunctionCall<Void>
			updateItemsFunctionCall
			= new BatchCommandSqlFunctionCall(updateItemsFunctionName,
			itemInParameters,
			userIdDao);
		SqlFunctionCall<Void>
			createItemsFunctionCall
			= new BatchCommandSqlFunctionCall(createItemsFunctionName,
			createInParameters,
			userIdDao);
		SqlFunctionCall<Void>
			deleteItemsFunctionCall
			= new BatchCommandSqlFunctionCall(deleteItemsFunctionName,
			deleteItemsInParameters,
			userIdDao);

		this.execute(updateGroupFunctionCall,
			updateItemsFunctionCall,
			createItemsFunctionCall,
			deleteItemsFunctionCall);
	}


	protected void executeDelete(
		String deleteGroupFunctionName,
		List<SqlFunctionParameter> deleteGroupInParameters,
		String deleteItemsFunctionName,
		List<SqlFunctionParameter> deleteItemsInParameters)
	{
		SqlFunctionCall<Void>
			deleteGroupFunctionCall
			= new SimpleCommandSqlFunctionCall<>(deleteGroupFunctionName,
			deleteGroupInParameters,
			null,
			userIdDao);
		SqlFunctionCall<Void>
			deleteItemsFunctionCall
			= new SimpleCommandSqlFunctionCall<>(deleteItemsFunctionName,
			deleteItemsInParameters,
			null,
			userIdDao);

		this.execute(deleteItemsFunctionCall, deleteGroupFunctionCall);
	}

	protected boolean canDelete(
		String functionName,
		List<SqlFunctionParameter> inParameters,
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

	private T addItemsToGroup(T crudItemGroup, List<V> crudItems)
	{
		crudItemGroup.setInspectionItems(crudItems);

		return crudItemGroup;
	}

	private List<T> partitionItemsForGroups(
		List<T> crudItemGroups, List<V> crudItems)
	{

		Map<Integer, T> crudItemGroupsById = crudItemGroups.stream()
			.collect(Collectors.toMap(CrudItemInspectionGroup::getId,
				Function.identity()));
		Map<Integer, List<V>> crudItemsByGroupId = crudItems.stream()
			.collect(Collectors.groupingBy(CrudItemInspection::getGroupId));

		crudItemGroupsById.forEach((id, crudItemGroup) -> crudItemGroup.setInspectionItems(
			crudItemsByGroupId.get(id)));

		return crudItemGroups;
	}

	private static class ParentIdSettingSqlFunctionCall
		extends SimpleCommandSqlFunctionCall<Integer>
	{
		private final List<List<SqlFunctionParameter>> itemInParameters;
		private final String parentIdColumnName;

		public ParentIdSettingSqlFunctionCall(
			String functionCallSql,
			List<SqlFunctionParameter> groupInParameters,
			List<List<SqlFunctionParameter>> itemInParameters,
			String groupIdColumnName,
			String parentIdColumnName,
			UserIdDao userIdDao)
		{
			super(functionCallSql,
				groupInParameters,
				new SimpleResultSetTransformer<>(rs -> rs.getInt(
					groupIdColumnName)),
				userIdDao);
			this.itemInParameters = itemInParameters;
			this.parentIdColumnName = parentIdColumnName;
		}

		@Override
		public void execute(PreparedStatement preparedStatement)
			throws SQLException
		{
			super.execute(preparedStatement);
			Integer parentId = this.getResult();

			// set the parentId in the itemInParameters
			itemInParameters.stream()
				.flatMap(List::stream)
				.filter(sqlFunctionParameter -> sqlFunctionParameter.getName()
					.equals(parentIdColumnName)
					&& sqlFunctionParameter instanceof IntegerSqlFunctionParameter)
				.map(sqlFunctionParameter -> (IntegerSqlFunctionParameter) sqlFunctionParameter)
				.forEach(sqlFunctionParameter -> sqlFunctionParameter.setValue(
					parentId));
		}
	}
}
