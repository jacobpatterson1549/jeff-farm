package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.Hive;
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
public class HiveDao extends SqlFunctionDao implements CrudDao<Hive>
{
	private final LoginDao loginDao;

	@Inject
	public HiveDao(DataSource dataSource, LoginDao loginDao)
	{
		super(dataSource);
		this.loginDao = loginDao;
	}

	@Override
	public int create(Hive hive)
	{
		return this.executeCreate(
				"create_hive",
				Arrays.asList(
						new SqlFunctionParameter<>(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER),
						new SqlFunctionParameter<>(Hive.NAME_COLUMN, hive.getName(), Types.VARCHAR),
						new SqlFunctionParameter<>(Hive.QUEEN_COLOR_COLUMN, hive.getQueenColorInteger(), Types.INTEGER)),
				Hive.ID_COLUMN,
				loginDao.getUserId());
	}

	@Override
	public Hive read(int id)
	{
		return this.executeRead(
				"read_hive",
				Collections.singletonList(
						new SqlFunctionParameter<>(Hive.ID_COLUMN, id, Types.INTEGER)),
				this::mapRow);
	}

	@Override
	public List<Hive> readList(int parentId)
	{
		return this.executeReadList(
				"read_hives",
				Collections.singletonList(
						new SqlFunctionParameter<>(Hive.FARM_ID_COLUMN, parentId, Types.INTEGER)),
				this::mapRow);
	}

	@Override
	public void update(int id, Hive hive)
	{
		this.executeUpdate(
				"update_hive",
				Arrays.asList(
						new SqlFunctionParameter<>(Hive.ID_COLUMN, id, Types.INTEGER),
						new SqlFunctionParameter<>(Hive.NAME_COLUMN, hive.getName(), Types.VARCHAR),
						new SqlFunctionParameter<>(Hive.QUEEN_COLOR_COLUMN, hive.getQueenColorInteger(), Types.INTEGER)),
				loginDao.getUserId());
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_hive",
				Collections.singletonList(
						new SqlFunctionParameter<>(Hive.ID_COLUMN, id, Types.INTEGER)),
				loginDao.getUserId());
	}
	
	@Override
	public boolean canDelete(int id)
	{
		return this.executeReadBoolean("can_delete_hive",
				Collections.singletonList(
						new SqlFunctionParameter<>(Hive.ID_COLUMN, id, Types.INTEGER)),
				Hive.CAN_DELETE_ITEM);
	}

	@Override
	public Hive mapRow(ResultSet rs) throws SQLException
	{
		return new Hive()
				.setId(rs.getInt(Hive.ID_COLUMN))
				.setFarmId(rs.getInt(Hive.FARM_ID_COLUMN))
				.setName(rs.getString(Hive.NAME_COLUMN))
				.setQueenColorInteger(rs.getInt(Hive.QUEEN_COLOR_COLUMN))
				.setCreatedTimestamp(rs.getTimestamp(Hive.CREATED_DATE_COLUMN))
				.setModifiedTimestamp(rs.getTimestamp(Hive.MODIFIED_DATE_COLUMN));
	}
}
