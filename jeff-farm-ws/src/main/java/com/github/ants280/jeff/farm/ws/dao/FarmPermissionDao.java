package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.FarmPermission;
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
		return this.executeCreate(
			"create_farm_permission",
			Arrays.asList(
				new IntegerSqlFunctionParameter(FarmPermission.FARM_ID_COLUMN, farmPermission.getFarmId()),
				new StringSqlFunctionParameter(FarmPermission.USER_NAME_COLUMN, farmPermission.getUserName())));
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
	public void update(
		int id, FarmPermission farmPermission)
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
			.setFarmId(rs.getInt(FarmPermission.FARM_ID_COLUMN))
			.setUserName(rs.getString(FarmPermission.USER_NAME_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(FarmPermission.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(FarmPermission.MODIFIED_DATE_COLUMN));
	}
}
