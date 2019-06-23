package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemInspectionGroupDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.CrudItemInspectionGroupUpdate;
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
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class PoultryInspectionGroupDao
	extends CrudItemInspectionGroupDao<PoultryInspection, PoultryInspectionGroup>
{
	private final PoultryDao poultryDao;

	@Inject
	public PoultryInspectionGroupDao(
		DataSource dataSource,
		PoultryDao poultryDao,
		UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
		this.poultryDao = poultryDao;
	}

	@Override
	public int create(PoultryInspectionGroup poultryInspectionGroup)
	{
		Function<PoultryInspection, List<SqlFunctionParameter>>
			itemParameterMapper
			= poultryInspection -> Arrays.asList(new IntegerSqlFunctionParameter(
				PoultryInspection.GROUP_ID_COLUMN,
				-1), // is reset by executeCreate()
			new IntegerSqlFunctionParameter(PoultryInspection.TARGET_ID_COLUMN,
				poultryInspection.getTargetId()),
			new IntegerSqlFunctionParameter(PoultryInspection.BIRD_COUNT_COLUMN,
				poultryInspection.getBirdCount()),
			new IntegerSqlFunctionParameter(PoultryInspection.EGG_COUNT_COLUMN,
				poultryInspection.getEggCount()));
		return this.executeCreate("create_poultry_inspection_group",
			Arrays.asList(new IntegerSqlFunctionParameter(
					PoultryInspectionGroup.FARM_ID_COLUMN,
					poultryInspectionGroup.getParentId()),
				new StringSqlFunctionParameter(PoultryInspectionGroup.NOTES_COLUMN,
					poultryInspectionGroup.getNotes())),
			"create_poultry_inspection",
			poultryInspectionGroup.getInspectionItems()
				.stream()
				.map(itemParameterMapper)
				.collect(Collectors.toList()),
			PoultryInspectionGroup.ID_COLUMN,
			PoultryInspection.GROUP_ID_COLUMN);
	}

	@Override
	public PoultryInspectionGroup read(int id)
	{
		return this.executeRead("read_poultry_inspection_group",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				PoultryInspectionGroup.ID_COLUMN,
				id)),
			"read_poultry_inspections_for_group",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				PoultryInspection.GROUP_ID_COLUMN,
				id)));
	}

	@Override
	public List<PoultryInspectionGroup> readList(int farmId)
	{
		return this.executeReadList("read_poultry_inspection_groups",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				PoultryInspectionGroup.ID_COLUMN,
				farmId)),
			"read_poultry_inspections_for_farm",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				PoultryInspectionGroup.ID_COLUMN,
				farmId)));
	}

	@Override
	public void update(
		CrudItemInspectionGroupUpdate<PoultryInspection, PoultryInspectionGroup> poultryInspectionGroupUpdate)
	{
		Function<PoultryInspection, List<SqlFunctionParameter>>
			updateParameterMapper
			= poultryInspection -> Arrays.asList(new IntegerSqlFunctionParameter(PoultryInspection.ID_COLUMN,
				poultryInspection.getId()),
			new IntegerSqlFunctionParameter(PoultryInspection.BIRD_COUNT_COLUMN,
				poultryInspection.getBirdCount()),
			new IntegerSqlFunctionParameter(PoultryInspection.EGG_COUNT_COLUMN,
				poultryInspection.getEggCount()));
		Function<PoultryInspection, List<SqlFunctionParameter>>
			addItemParameterMapper
			= poultryInspection -> Arrays.asList(new IntegerSqlFunctionParameter(PoultryInspection.GROUP_ID_COLUMN,
				poultryInspectionGroupUpdate.getGroup().getId()),
			new IntegerSqlFunctionParameter(PoultryInspection.TARGET_ID_COLUMN,
				poultryInspection.getTargetId()),
			new IntegerSqlFunctionParameter(PoultryInspection.BIRD_COUNT_COLUMN,
				poultryInspection.getBirdCount()),
			new IntegerSqlFunctionParameter(PoultryInspection.EGG_COUNT_COLUMN,
				poultryInspection.getEggCount()));
		IntFunction<List<SqlFunctionParameter>>
			deleteItemParameterMapper
			= itemId -> Collections.singletonList(new IntegerSqlFunctionParameter(
			PoultryInspection.ID_COLUMN,
			itemId));
		this.executeUpdate("update_poultry_inspection_group",
			Arrays.asList(new IntegerSqlFunctionParameter(PoultryInspectionGroup.ID_COLUMN,
					poultryInspectionGroupUpdate.getGroup().getId()),
				new StringSqlFunctionParameter(
					PoultryInspectionGroup.NOTES_COLUMN,
					poultryInspectionGroupUpdate.getGroup().getNotes())),
			"update_poultry_inspection",
			poultryInspectionGroupUpdate.getGroup()
				.getInspectionItems()
				.stream()
				.map(updateParameterMapper)
				.collect(Collectors.toList()),
			"create_poultry_inspection",
			Arrays.stream(poultryInspectionGroupUpdate.getAddItems())
				.map(addItemParameterMapper)
				.collect(Collectors.toList()),
			"delete_poultry_inspection",
			IntStream.of(poultryInspectionGroupUpdate.getRemoveItemIds())
				.mapToObj(deleteItemParameterMapper)
				.collect(Collectors.toList()));
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
			"delete_poultry_inspections_for_group",
			groupIdInParameterList);
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
			.collect(Collectors.toMap(Poultry::getId, Poultry::getName));
	}

	@Override
	public PoultryInspectionGroup mapGroup(ResultSet rs) throws SQLException
	{
		return new PoultryInspectionGroup().setId(rs.getInt(
			PoultryInspectionGroup.ID_COLUMN))
			.setParentId(rs.getInt(PoultryInspectionGroup.FARM_ID_COLUMN))
			.setNotes(rs.getString(PoultryInspectionGroup.NOTES_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(PoultryInspectionGroup.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(PoultryInspectionGroup.MODIFIED_DATE_COLUMN));
	}

	@Override
	public PoultryInspection mapItem(ResultSet rs) throws SQLException
	{
		return new PoultryInspection().setId(rs.getInt(PoultryInspection.ID_COLUMN))
			.setParentId(rs.getInt(PoultryInspection.GROUP_ID_COLUMN))
			.setTargetId(rs.getInt(PoultryInspection.TARGET_ID_COLUMN))
			.setTargetName(rs.getString(PoultryInspection.TARGET_NAME_COLUMN))
			.setBirdCount(rs.getInt(PoultryInspection.BIRD_COUNT_COLUMN))
			.setEggCount(rs.getInt(PoultryInspection.EGG_COUNT_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(PoultryInspection.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(PoultryInspection.MODIFIED_DATE_COLUMN));
	}
}
