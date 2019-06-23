package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemInspectionGroupDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.BooleanSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.CrudItemInspectionGroupUpdate;
import com.github.ants280.jeff.farm.ws.model.Hive;
import com.github.ants280.jeff.farm.ws.model.HiveInspection;
import com.github.ants280.jeff.farm.ws.model.HiveInspectionGroup;
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
public class HiveInspectionGroupDao
	extends CrudItemInspectionGroupDao<HiveInspection, HiveInspectionGroup>
{
	private final HiveDao hiveDao;

	@Inject
	public HiveInspectionGroupDao(
		DataSource dataSource,
		HiveDao hiveDao,
		UserIdDao userIdDao)
	{
		super(dataSource, userIdDao);
		this.hiveDao = hiveDao;
	}

	@Override
	public int create(HiveInspectionGroup hiveInspectionGroup)
	{
		Function<HiveInspection, List<SqlFunctionParameter>>
			itemParameterMapper
			= hiveInspection -> Arrays.asList(
			new IntegerSqlFunctionParameter(HiveInspection.GROUP_ID_COLUMN, -1), // is reset by executeCreate()
			new IntegerSqlFunctionParameter(HiveInspection.TARGET_ID_COLUMN, hiveInspection.getTargetId()),
			new BooleanSqlFunctionParameter(HiveInspection.QUEEN_SEEN_COLUMN, hiveInspection.getQueenSeen()),
			new BooleanSqlFunctionParameter(HiveInspection.EGGS_SEEN_COLUMN, hiveInspection.getEggsSeen()),
			new IntegerSqlFunctionParameter(HiveInspection.LAYING_PATTERN_STARS_COLUMN, hiveInspection.getLayingPatternStars()),
			new IntegerSqlFunctionParameter(HiveInspection.TEMPERAMENT_STARS_COLUMN, hiveInspection.getTemperamentStars()),
			new IntegerSqlFunctionParameter(HiveInspection.QUEEN_CELLS_COLUMN, hiveInspection.getQueenCells()),
			new IntegerSqlFunctionParameter(HiveInspection.SUPERSEDURE_CELLS_COLUMN, hiveInspection.getSupersedureCells()),
			new IntegerSqlFunctionParameter(HiveInspection.SWARM_CELLS_COLUMN, hiveInspection.getSwarmCells()),
			new IntegerSqlFunctionParameter(HiveInspection.COMB_BUILDING_STARS_COLUMN, hiveInspection.getCombBuildingStars()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_SEALED_BROOD_COLUMN, hiveInspection.getFramesSealedBrood()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_OPEN_BROOD_COLUMN, hiveInspection.getFramesOpenBrood()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_HONEY_COLUMN, hiveInspection.getFramesHoney()),
			new StringSqlFunctionParameter(HiveInspection.WEATHER_COLUMN, hiveInspection.getWeather()),
			new IntegerSqlFunctionParameter(HiveInspection.TEMPERATURE_F_COLUMN, hiveInspection.getTemperatureF()),
			new IntegerSqlFunctionParameter(HiveInspection.WIND_SPEED_MPH_COLUMN, hiveInspection.getWindSpeedMph()));
		return this.executeCreate("create_hive_inspection_group",
			Arrays.asList(new IntegerSqlFunctionParameter(
					HiveInspectionGroup.FARM_ID_COLUMN,
					hiveInspectionGroup.getParentId()),
				new StringSqlFunctionParameter(HiveInspectionGroup.NOTES_COLUMN,
					hiveInspectionGroup.getNotes())),
			"create_hive_inspection",
			hiveInspectionGroup.getInspectionItems()
				.stream()
				.map(itemParameterMapper)
				.collect(Collectors.toList()),
			HiveInspectionGroup.ID_COLUMN,
			HiveInspection.GROUP_ID_COLUMN);
	}

	@Override
	public HiveInspectionGroup read(int id)
	{
		return this.executeRead("read_hive_inspection_group",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				HiveInspectionGroup.ID_COLUMN,
				id)),
			"read_hive_inspections_for_group",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				HiveInspection.GROUP_ID_COLUMN,
				id)));
	}

	@Override
	public List<HiveInspectionGroup> readList(int farmId)
	{
		return this.executeReadList("read_hive_inspection_groups",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				HiveInspectionGroup.ID_COLUMN,
				farmId)),
			"read_hive_inspections_for_farm",
			Collections.singletonList(new IntegerSqlFunctionParameter(
				HiveInspectionGroup.ID_COLUMN,
				farmId)));
	}

	@Override
	public void update(
		CrudItemInspectionGroupUpdate<HiveInspection, HiveInspectionGroup> hiveInspectionGroupUpdate)
	{
		Function<HiveInspection, List<SqlFunctionParameter>>
			updateParameterMapper
			= hiveInspection -> Arrays.asList(new IntegerSqlFunctionParameter(HiveInspection.ID_COLUMN, hiveInspection.getId()),
			new BooleanSqlFunctionParameter(HiveInspection.QUEEN_SEEN_COLUMN, hiveInspection.getQueenSeen()),
			new BooleanSqlFunctionParameter(HiveInspection.EGGS_SEEN_COLUMN, hiveInspection.getEggsSeen()),
			new IntegerSqlFunctionParameter(HiveInspection.LAYING_PATTERN_STARS_COLUMN, hiveInspection.getLayingPatternStars()),
			new IntegerSqlFunctionParameter(HiveInspection.TEMPERAMENT_STARS_COLUMN, hiveInspection.getTemperamentStars()),
			new IntegerSqlFunctionParameter(HiveInspection.QUEEN_CELLS_COLUMN, hiveInspection.getQueenCells()),
			new IntegerSqlFunctionParameter(HiveInspection.SUPERSEDURE_CELLS_COLUMN, hiveInspection.getSupersedureCells()),
			new IntegerSqlFunctionParameter(HiveInspection.SWARM_CELLS_COLUMN, hiveInspection.getSwarmCells()),
			new IntegerSqlFunctionParameter(HiveInspection.COMB_BUILDING_STARS_COLUMN, hiveInspection.getCombBuildingStars()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_SEALED_BROOD_COLUMN, hiveInspection.getFramesSealedBrood()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_OPEN_BROOD_COLUMN, hiveInspection.getFramesOpenBrood()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_HONEY_COLUMN, hiveInspection.getFramesHoney()),
			new StringSqlFunctionParameter(HiveInspection.WEATHER_COLUMN, hiveInspection.getWeather()),
			new IntegerSqlFunctionParameter(HiveInspection.TEMPERATURE_F_COLUMN, hiveInspection.getTemperatureF()),
			new IntegerSqlFunctionParameter(HiveInspection.WIND_SPEED_MPH_COLUMN, hiveInspection.getWindSpeedMph()));
		Function<HiveInspection, List<SqlFunctionParameter>>
			addItemParameterMapper
			= hiveInspection -> Arrays.asList(new IntegerSqlFunctionParameter(HiveInspection.GROUP_ID_COLUMN,
				hiveInspectionGroupUpdate.getGroup().getId()),
			new IntegerSqlFunctionParameter(HiveInspection.TARGET_ID_COLUMN, hiveInspection.getTargetId()),
			new BooleanSqlFunctionParameter(HiveInspection.QUEEN_SEEN_COLUMN, hiveInspection.getQueenSeen()),
			new BooleanSqlFunctionParameter(HiveInspection.EGGS_SEEN_COLUMN, hiveInspection.getEggsSeen()),
			new IntegerSqlFunctionParameter(HiveInspection.LAYING_PATTERN_STARS_COLUMN, hiveInspection.getLayingPatternStars()),
			new IntegerSqlFunctionParameter(HiveInspection.TEMPERAMENT_STARS_COLUMN, hiveInspection.getTemperamentStars()),
			new IntegerSqlFunctionParameter(HiveInspection.QUEEN_CELLS_COLUMN, hiveInspection.getQueenCells()),
			new IntegerSqlFunctionParameter(HiveInspection.SUPERSEDURE_CELLS_COLUMN, hiveInspection.getSupersedureCells()),
			new IntegerSqlFunctionParameter(HiveInspection.SWARM_CELLS_COLUMN, hiveInspection.getSwarmCells()),
			new IntegerSqlFunctionParameter(HiveInspection.COMB_BUILDING_STARS_COLUMN, hiveInspection.getCombBuildingStars()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_SEALED_BROOD_COLUMN, hiveInspection.getFramesSealedBrood()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_OPEN_BROOD_COLUMN, hiveInspection.getFramesOpenBrood()),
			new IntegerSqlFunctionParameter(HiveInspection.FRAMES_HONEY_COLUMN, hiveInspection.getFramesHoney()),
			new StringSqlFunctionParameter(HiveInspection.WEATHER_COLUMN, hiveInspection.getWeather()),
			new IntegerSqlFunctionParameter(HiveInspection.TEMPERATURE_F_COLUMN, hiveInspection.getTemperatureF()),
			new IntegerSqlFunctionParameter(HiveInspection.WIND_SPEED_MPH_COLUMN, hiveInspection.getWindSpeedMph()));
		IntFunction<List<SqlFunctionParameter>>
			deleteItemParameterMapper
			= itemId -> Collections.singletonList(new IntegerSqlFunctionParameter(
			HiveInspection.ID_COLUMN,
			itemId));
		this.executeUpdate("update_hive_inspection_group",
			Arrays.asList(new IntegerSqlFunctionParameter(HiveInspectionGroup.ID_COLUMN,
					hiveInspectionGroupUpdate.getGroup().getId()),
				new StringSqlFunctionParameter(
					HiveInspectionGroup.NOTES_COLUMN,
					hiveInspectionGroupUpdate.getGroup().getNotes())),
			"update_hive_inspection",
			hiveInspectionGroupUpdate.getGroup()
				.getInspectionItems()
				.stream()
				.map(updateParameterMapper)
				.collect(Collectors.toList()),
			"create_hive_inspection",
			Arrays.stream(hiveInspectionGroupUpdate.getAddItems())
				.map(addItemParameterMapper)
				.collect(Collectors.toList()),
			"delete_hive_inspection",
			IntStream.of(hiveInspectionGroupUpdate.getRemoveItemIds())
				.mapToObj(deleteItemParameterMapper)
				.collect(Collectors.toList()));
	}

	@Override
	public void delete(int id)
	{
		List<SqlFunctionParameter>
			groupIdInParameterList
			= Collections.singletonList(new IntegerSqlFunctionParameter(
			HiveInspection.ID_COLUMN,
			id));
		this.executeDelete("delete_hive_inspection_group",
			groupIdInParameterList,
			"delete_hive_inspections",
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
		List<Hive> targets = hiveDao.readList(parentId);
		return targets.stream()
			.collect(Collectors.toMap(Hive::getId, Hive::getName));
	}

	@Override
	public HiveInspectionGroup mapGroup(ResultSet rs) throws SQLException
	{
		return new HiveInspectionGroup().setId(rs.getInt(
			HiveInspectionGroup.ID_COLUMN))
			.setParentId(rs.getInt(HiveInspectionGroup.FARM_ID_COLUMN))
			.setNotes(rs.getString(HiveInspectionGroup.NOTES_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(HiveInspectionGroup.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(HiveInspectionGroup.MODIFIED_DATE_COLUMN));
	}

	@Override
	public HiveInspection mapItem(ResultSet rs) throws SQLException
	{
		return new HiveInspection().setId(rs.getInt(HiveInspection.ID_COLUMN))
			.setParentId(rs.getInt(HiveInspection.GROUP_ID_COLUMN))
			.setTargetId(rs.getInt(HiveInspection.TARGET_ID_COLUMN))
			.setQueenSeen(rs.getBoolean(HiveInspection.QUEEN_SEEN_COLUMN))
			.setEggsSeen(rs.getBoolean(HiveInspection.EGGS_SEEN_COLUMN))
			.setLayingPatternStars(rs.getInt(HiveInspection.LAYING_PATTERN_STARS_COLUMN))
			.setTemperamentStars(rs.getInt(HiveInspection.TEMPERAMENT_STARS_COLUMN))
			.setQueenCells(rs.getInt(HiveInspection.QUEEN_CELLS_COLUMN))
			.setSupersedureCells(rs.getInt(HiveInspection.SUPERSEDURE_CELLS_COLUMN))
			.setSwarmCells(rs.getInt(HiveInspection.SWARM_CELLS_COLUMN))
			.setCombBuildingStars(rs.getInt(HiveInspection.COMB_BUILDING_STARS_COLUMN))
			.setFramesSealedBrood(rs.getInt(HiveInspection.FRAMES_SEALED_BROOD_COLUMN))
			.setFramesOpenBrood(rs.getInt(HiveInspection.FRAMES_OPEN_BROOD_COLUMN))
			.setFramesHoney(rs.getInt(HiveInspection.FRAMES_HONEY_COLUMN))
			.setWeather(rs.getString(HiveInspection.WEATHER_COLUMN))
			.setTemperatureF(rs.getInt(HiveInspection.TEMPERATURE_F_COLUMN))
			.setWindSpeedMph(rs.getInt(HiveInspection.WIND_SPEED_MPH_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(HiveInspection.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(HiveInspection.MODIFIED_DATE_COLUMN));
	}
}
