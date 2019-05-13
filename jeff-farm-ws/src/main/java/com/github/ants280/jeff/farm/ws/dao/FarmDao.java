package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.Farm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class FarmDao extends CrudItemDao<Farm>
{
	private final LoginDao loginDao;

	@Inject
	public FarmDao(DataSource dataSource, LoginDao loginDao)
	{
		super(dataSource);
		this.loginDao = loginDao;
	}

	@Override
	public int create(Farm farm)
	{
		return this.executeCreate(
				"create_farm",
				Arrays.asList(
						new SqlFunctionParameter<>(Farm.NAME_COLUMN, farm.getName(), Types.VARCHAR),
						new SqlFunctionParameter<>(Farm.LOCATION_COLUMN, farm.getLocation(), Types.VARCHAR)),
			loginDao.getUserId());
	}

	@Override
	public Farm read(int id)
	{
		return this.executeRead(
				"read_farm",
				Collections.singletonList(
						new SqlFunctionParameter<>(Farm.ID_COLUMN, id, Types.INTEGER)),
				this::mapRow);
	}

	@Override
	public List<Farm> readList(int parentId)
	{
		return this.executeReadList(
				"read_farms",
				Collections.emptyList(),
				this::mapRow);
	}

	@Override
	public void update(int id, Farm farm)
	{
		this.executeUpdate(
				"update_farm",
				Arrays.asList(
						new SqlFunctionParameter<>(Farm.ID_COLUMN, id, Types.INTEGER),
						new SqlFunctionParameter<>(Farm.NAME_COLUMN, farm.getName(), Types.VARCHAR),
						new SqlFunctionParameter<>(Farm.LOCATION_COLUMN, farm.getLocation(), Types.VARCHAR)),
				loginDao.getUserId());
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_farm",
				Collections.singletonList(
						new SqlFunctionParameter<>(Farm.ID_COLUMN, id, Types.INTEGER)),
				loginDao.getUserId());
	}
	
	@Override
	public boolean canDelete(int id)
	{
		return this.canDelete("can_delete_farm",
				Collections.singletonList(
						new SqlFunctionParameter<>(Farm.ID_COLUMN, id, Types.INTEGER)),
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
