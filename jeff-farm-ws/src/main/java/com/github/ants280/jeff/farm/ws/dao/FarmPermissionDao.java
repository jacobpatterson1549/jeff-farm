package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.dao.api.call.SideEffectSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SimpleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.SimpleResultSetTransformer;
import com.github.ants280.jeff.farm.ws.model.CrudItem;
import com.github.ants280.jeff.farm.ws.model.FarmPermission;
import com.github.ants280.jeff.farm.ws.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class FarmPermissionDao extends CrudItemDao<FarmPermission>
{
	@Inject
	public FarmPermissionDao(
		DataSource dataSource, UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
	}

	@Override
	public int create(FarmPermission farmPermission)
	{
		List<SqlFunctionParameter<?>> createFarmPermissionInParameters = Arrays.asList(
			new IntegerSqlFunctionParameter(FarmPermission.FARM_ID_COLUMN, farmPermission.getParentId()),
			new IntegerSqlFunctionParameter(FarmPermission.USER_ID_COLUMN, -1)); // filled in below
		SqlFunctionCall<Integer> userNameCheckingFunctionCall
			= new SideEffectSqlFunctionCall<>(
				"read_user_from_user_name",
				Collections.singletonList(new StringSqlFunctionParameter(
					User.USER_NAME_COLUMN,
					farmPermission.getUserName())),
				new SimpleResultSetTransformer<>(rs -> rs.getInt(User.ID_COLUMN)),
				userId -> this.setUserId(userId, createFarmPermissionInParameters),
				null); // unchecked read
		SqlFunctionCall<Integer>
			createFunctionCall
			= new SimpleCommandSqlFunctionCall<>("create_farm_permission",
			createFarmPermissionInParameters,
			new SimpleResultSetTransformer<>(resultSet -> resultSet.getInt(
				CrudItem.ID_COLUMN)),
			userIdDao);
		return this.execute(
			(userId, createdFarmPermissionId) -> createdFarmPermissionId,
			userNameCheckingFunctionCall,
			createFunctionCall);
	}

	@Override
	public FarmPermission read(int id)
	{
		return this.executeRead(
			"read_farm_permission",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(FarmPermission.ID_COLUMN, id)));
	}

	@Override
	public List<FarmPermission> readList(int farmId)
	{
		return this.executeReadList(
			"read_farm_permissions",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(FarmPermission.FARM_ID_COLUMN, farmId)));
	}

	@Override
	public void update(FarmPermission farmPermission)
	{
		throw new UnsupportedOperationException("Cannot update FarmPermission.  Delete it instead.");
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
			"delete_farm_permission",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(FarmPermission.ID_COLUMN, id)));
	}

	@Override
	public boolean canDelete(int id)
	{
		return this.canDelete("can_delete_farm_permission",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(FarmPermission.ID_COLUMN, id)),
			FarmPermission.CAN_DELETE_ITEM);
	}

	@Override
	protected FarmPermission mapRow(ResultSet rs) throws SQLException
	{
		return new FarmPermission()
			.setId(rs.getInt(FarmPermission.ID_COLUMN))
			.setParentId(rs.getInt(FarmPermission.FARM_ID_COLUMN))
			.setUserName(rs.getString(FarmPermission.USER_NAME_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(FarmPermission.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(FarmPermission.MODIFIED_DATE_COLUMN));
	}

	private void setUserId(Integer userId, List<SqlFunctionParameter<?>> createFarmPermissionInParameters)
	{
		if (userId == null)
		{
			throw new JeffFarmWsException("No user with username.");
		}

		createFarmPermissionInParameters.stream()
			.filter(sqlFunctionParameter -> sqlFunctionParameter.getName()
				.equals(FarmPermission.USER_ID_COLUMN)
				&& sqlFunctionParameter instanceof IntegerSqlFunctionParameter)
			.map(IntegerSqlFunctionParameter.class::cast)
			.forEach(sqlFunctionParameter -> sqlFunctionParameter.setValue(userId));
	}
}
