package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.Livestock;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class LivestockDao extends CrudItemDao<Livestock>
{
	@Inject
	public LivestockDao(DataSource dataSource, UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
	}

	@Override
	public int create(Livestock livestock)
	{
		return this.executeCreate(
			"create_livestock",
			Arrays.asList(
				new IntegerSqlFunctionParameter(Livestock.FARM_ID_COLUMN, livestock.getParentId()),
				new StringSqlFunctionParameter(Livestock.NAME_COLUMN, livestock.getName())));
	}

	@Override
	public Livestock read(int id)
	{
		return this.executeRead(
			"read_livestock",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(Livestock.ID_COLUMN, id)));
	}

	@Override
	public List<Livestock> readList(int parentId)
	{
		return this.executeReadList(
			"read_livestock_list",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(Livestock.FARM_ID_COLUMN, parentId)));
	}

	@Override
	public void update(Livestock livestock)
	{
		this.executeUpdate(
			"update_livestock",
			Arrays.asList(
				new IntegerSqlFunctionParameter(Livestock.ID_COLUMN, livestock.getId()),
				new StringSqlFunctionParameter(Livestock.NAME_COLUMN, livestock.getName())));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
			"delete_livestock",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(Livestock.ID_COLUMN, id)));
	}

	@Override
	public boolean canDelete(int id)
	{
		return this.canDelete("can_delete_livestock",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(Livestock.ID_COLUMN, id)),
			Livestock.CAN_DELETE_ITEM);
	}

	@Override
	public Livestock mapRow(ResultSet rs) throws SQLException
	{
		return new Livestock()
			.setId(rs.getInt(Livestock.ID_COLUMN))
			.setParentId(rs.getInt(Livestock.FARM_ID_COLUMN))
			.setName(rs.getString(Livestock.NAME_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(Livestock.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(Livestock.MODIFIED_DATE_COLUMN));
	}
}
