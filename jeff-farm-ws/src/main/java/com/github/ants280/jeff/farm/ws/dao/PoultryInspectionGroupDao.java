package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemInspectionGroupDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.Poultry;
import com.github.ants280.jeff.farm.ws.model.PoultryInspection;
import com.github.ants280.jeff.farm.ws.model.PoultryInspectionGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class PoultryInspectionGroupDao
	extends CrudItemInspectionGroupDao<PoultryInspection, PoultryInspectionGroup>
{
	private final PoultryDao poultryDao;
	private final LoginDao loginDao;

	@Inject
	public PoultryInspectionGroupDao(
		DataSource dataSource,
		PoultryDao poultryDao,
		LoginDao loginDao)
	{
		super(dataSource);
		this.poultryDao = poultryDao;
		this.loginDao = loginDao;
	}

	@Override
	public int create(PoultryInspectionGroup poultryInspectionGroup)
	{
		Function<PoultryInspection, List<SqlFunctionParameter>>
			itemParameterMapper
			= poultryInspection -> Arrays.asList(new IntegerSqlFunctionParameter(
				PoultryInspection.GROUP_ID_COLUMN,
				poultryInspection.getGroupId()),
			new IntegerSqlFunctionParameter(PoultryInspection.TARGET_ID_COLUMN,
				poultryInspection.getTargetId()),
			new IntegerSqlFunctionParameter(PoultryInspection.BIRD_COUNT_COLUMN,
				poultryInspection.getBirdCount()),
			new IntegerSqlFunctionParameter(PoultryInspection.EGG_COUNT_COLUMN,
				poultryInspection.getEggCount()));
		return this.executeCreate("create_poultry_inspection_group",
			Arrays.asList(new IntegerSqlFunctionParameter(
					PoultryInspectionGroup.FARM_ID_COLUMN,
					poultryInspectionGroup.getFarmId()),
				new StringSqlFunctionParameter(PoultryInspectionGroup.NOTES_COLUMN,
					poultryInspectionGroup.getNotes())),
			"create_poultry_inspection",
			poultryInspectionGroup.getInspectionItems()
				.stream()
				.map(itemParameterMapper)
				.collect(Collectors.toList()),
			PoultryInspectionGroup.ID_COLUMN,
			PoultryInspection.GROUP_ID_COLUMN,
			loginDao.getUserId());
	}

	@Override
	public PoultryInspectionGroup read(int id)
	{
		return this.executeRead("read_poultry_inspection_group",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				PoultryInspection.ID_COLUMN,
				id)),
			"read_poultry_inspections",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				PoultryInspection.GROUP_ID_COLUMN,
				id)));
	}

	@Override
	public List<PoultryInspectionGroup> readList(int parentId)
	{
		return this.executeReadList("read_poultry_inspection_groups",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				PoultryInspectionGroup.ID_COLUMN,
				parentId)),
			"read_poultry_inspections",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				PoultryInspection.GROUP_ID_COLUMN,
				parentId)));
	}

	@Override
	public void update(
		int id, PoultryInspectionGroup poultryInspectionGroup)
	{
		Function<PoultryInspection, List<SqlFunctionParameter>>
			itemParameterMapper
			= poultryInspection -> Arrays.asList(new IntegerSqlFunctionParameter(PoultryInspection.ID_COLUMN,
				poultryInspection.getId()),
			new IntegerSqlFunctionParameter(PoultryInspection.BIRD_COUNT_COLUMN,
				poultryInspection.getBirdCount()),
			new IntegerSqlFunctionParameter(PoultryInspection.EGG_COUNT_COLUMN,
				poultryInspection.getEggCount()));
		this.executeUpdate("update_poultry_inspection_group",
			Arrays.asList(new IntegerSqlFunctionParameter(PoultryInspectionGroup.ID_COLUMN,
					id),
				new StringSqlFunctionParameter(PoultryInspectionGroup.NOTES_COLUMN,
					poultryInspectionGroup.getNotes())),
			"update_poultry_inspection",
			poultryInspectionGroup.getInspectionItems()
				.stream()
				.map(itemParameterMapper)
				.collect(Collectors.toList()),
			loginDao.getUserId());
	}

	@Override
	public void delete(int id)
	{
		List<SqlFunctionParameter>
			groupIdInParameterList
			= Collections.singletonList(new IntegerSqlFunctionParameter(PoultryInspection.ID_COLUMN,
			id));
		this.executeDelete("delete_poultry_inspection_group",
			groupIdInParameterList,
			"delete_poultry_inspections",
			groupIdInParameterList,
			loginDao.getUserId());
	}

	@Override
	public boolean canDelete(int id)
	{
		return true;
	}

	@Override
	public Map<Integer, String> getTargets(int parentId)
	{
		List<Poultry> targets = poultryDao.readList(parentId);
		return targets.stream()
			.collect(Collectors.toMap(
				Poultry::getId,
				Poultry::getName));
	}

	@Override
	public PoultryInspectionGroup mapGroup(ResultSet rs) throws SQLException
	{
		return new PoultryInspectionGroup().setId(rs.getInt(
			PoultryInspectionGroup.ID_COLUMN))
			.setFarmId(rs.getInt(PoultryInspectionGroup.FARM_ID_COLUMN))
			.setNotes(rs.getString(PoultryInspectionGroup.NOTES_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(PoultryInspectionGroup.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(PoultryInspectionGroup.MODIFIED_DATE_COLUMN));
	}

	@Override
	public PoultryInspection mapItem(ResultSet rs) throws SQLException
	{
		return new PoultryInspection().setId(rs.getInt(PoultryInspection.ID_COLUMN))
			.setGroupId(rs.getInt(PoultryInspection.GROUP_ID_COLUMN))
			.setTargetId(rs.getInt(PoultryInspection.TARGET_ID_COLUMN))
			.setTargetName(rs.getString(PoultryInspection.TARGET_NAME_COLUMN))
			.setBirdCount(rs.getInt(PoultryInspection.BIRD_COUNT_COLUMN))
			.setEggCount(rs.getInt(PoultryInspection.EGG_COUNT_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(PoultryInspection.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(PoultryInspection.MODIFIED_DATE_COLUMN));
	}
}
