package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.Cattle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class CattleDao extends CrudItemDao<Cattle>
{
	@Inject
	public CattleDao(DataSource dataSource, UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
	}

	@Override
	public int create(Cattle cattle)
	{
		return this.executeCreate(
			"create_cattle",
			Arrays.asList(
				new IntegerSqlFunctionParameter(Cattle.FARM_ID_COLUMN, cattle.getParentId()),
				new StringSqlFunctionParameter(Cattle.NAME_COLUMN, cattle.getName())));
	}

	@Override
	public Cattle read(int id)
	{
		return this.executeRead(
			"read_cattle",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(Cattle.ID_COLUMN, id)));
	}

	@Override
	public List<Cattle> readList(int parentId)
	{
		return this.executeReadList(
			"read_cattle_list",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(Cattle.FARM_ID_COLUMN, parentId)));
	}

	@Override
	public void update(Cattle cattle)
	{
		this.executeUpdate(
			"update_cattle",
			Arrays.asList(
				new IntegerSqlFunctionParameter(Cattle.ID_COLUMN, cattle.getId()),
				new StringSqlFunctionParameter(Cattle.NAME_COLUMN, cattle.getName())));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
			"delete_cattle",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(Cattle.ID_COLUMN, id)));
	}

	@Override
	public boolean canDelete(int id)
	{
		return this.canDelete("can_delete_cattle",
			Collections.singletonList(
				new IntegerSqlFunctionParameter(Cattle.ID_COLUMN, id)),
			Cattle.CAN_DELETE_ITEM);
	}

	@Override
	public Cattle mapRow(ResultSet rs) throws SQLException
	{
		return new Cattle()
			.setId(rs.getInt(Cattle.ID_COLUMN))
			.setParentId(rs.getInt(Cattle.FARM_ID_COLUMN))
			.setName(rs.getString(Cattle.NAME_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(Cattle.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(Cattle.MODIFIED_DATE_COLUMN));
	}
}
