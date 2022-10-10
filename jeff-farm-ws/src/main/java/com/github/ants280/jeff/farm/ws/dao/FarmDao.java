package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.Farm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class FarmDao extends CrudItemDao<Farm>
{
	@Inject
	public FarmDao(DataSource dataSource, UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
	}

	@Override
	public int create(Farm farm)
	{
		return this.executeCreate(
				"create_farm",
				Arrays.asList(
						new StringSqlFunctionParameter(Farm.NAME_COLUMN, farm.getName()),
						new StringSqlFunctionParameter(Farm.LOCATION_COLUMN, farm.getLocation())),
				Farm.ID_COLUMN);
	}

	@Override
	public Farm read(int id)
	{
		return this.executeRead(
				"read_farm",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(Farm.ID_COLUMN, id)));
	}

	@Override
	public List<Farm> readList(int parentId) // (user_id provided by SqlFunctionCall)
	{
		return this.executeReadList(
				"read_farms",
				Collections.emptyList());
	}

	@Override
	public void update(Farm farm)
	{
		this.executeUpdate(
				"update_farm",
				Arrays.asList(
						new IntegerSqlFunctionParameter(Farm.ID_COLUMN, farm.getId()),
						new StringSqlFunctionParameter(Farm.NAME_COLUMN, farm.getName()),
						new StringSqlFunctionParameter(Farm.LOCATION_COLUMN, farm.getLocation())));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_farm",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(Farm.ID_COLUMN, id)));
	}
	
	@Override
	public boolean canDelete(int id)
	{
		return this.canDelete("can_delete_farm",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(Farm.ID_COLUMN, id)),
				Farm.CAN_DELETE_ITEM);
	}

	@Override
	public Farm mapRow(ResultSet rs) throws SQLException
	{
		return new Farm()
				.setId(rs.getInt(Farm.ID_COLUMN))
				.setName(rs.getString(Farm.NAME_COLUMN))
				.setLocation(rs.getString(Farm.LOCATION_COLUMN))
				.setCreatedTimestamp(rs.getTimestamp(Farm.CREATED_DATE_COLUMN))
				.setModifiedTimestamp(rs.getTimestamp(Farm.MODIFIED_DATE_COLUMN));
	}
}
