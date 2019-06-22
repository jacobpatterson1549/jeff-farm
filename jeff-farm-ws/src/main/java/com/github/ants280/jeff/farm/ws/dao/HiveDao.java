package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.Hive;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class HiveDao extends CrudItemDao<Hive>
{
	@Inject
	public HiveDao(DataSource dataSource, UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
	}

	@Override
	public int create(Hive hive)
	{
		return this.executeCreate(
				"create_hive",
				Arrays.asList(
						new IntegerSqlFunctionParameter(Hive.FARM_ID_COLUMN, hive.getParentId()),
						new StringSqlFunctionParameter(Hive.NAME_COLUMN, hive.getName()),
						new IntegerSqlFunctionParameter(Hive.QUEEN_COLOR_COLUMN, hive.getQueenColorInteger())));
	}

	@Override
	public Hive read(int id)
	{
		return this.executeRead(
				"read_hive",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(Hive.ID_COLUMN, id)));
	}

	@Override
	public List<Hive> readList(int parentId)
	{
		return this.executeReadList(
				"read_hives",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(Hive.FARM_ID_COLUMN, parentId)));
	}

	@Override
	public void update(Hive hive)
	{
		this.executeUpdate(
				"update_hive",
				Arrays.asList(
						new IntegerSqlFunctionParameter(Hive.ID_COLUMN, hive.getId()),
						new StringSqlFunctionParameter(Hive.NAME_COLUMN, hive.getName()),
						new IntegerSqlFunctionParameter(Hive.QUEEN_COLOR_COLUMN, hive.getQueenColorInteger())));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_hive",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(Hive.ID_COLUMN, id)));
	}

	@Override
	public boolean canDelete(int id)
	{
		return this.canDelete("can_delete_hive",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(Hive.ID_COLUMN, id)),
				Hive.CAN_DELETE_ITEM);
	}

	@Override
	public Hive mapRow(ResultSet rs) throws SQLException
	{
		return new Hive()
				.setId(rs.getInt(Hive.ID_COLUMN))
				.setParentId(rs.getInt(Hive.FARM_ID_COLUMN))
				.setName(rs.getString(Hive.NAME_COLUMN))
				.setQueenColorInteger(rs.getInt(Hive.QUEEN_COLOR_COLUMN))
				.setCreatedTimestamp(rs.getTimestamp(Hive.CREATED_DATE_COLUMN))
				.setModifiedTimestamp(rs.getTimestamp(Hive.MODIFIED_DATE_COLUMN));
	}
}
