package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.parameter.BooleanSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.StringSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.model.HiveInspection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class HiveInspectionDao extends CrudItemDao<HiveInspection>
{
	private final LoginDao loginDao;

	@Inject
	public HiveInspectionDao(DataSource dataSource, LoginDao loginDao)
	{
		super(dataSource);
		this.loginDao = loginDao;
	}

	@Override
	public int create(HiveInspection hiveInspection)
	{
		return this.executeCreate(
				"create_hive_inspection",
				Arrays.asList(
						new IntegerSqlFunctionParameter(HiveInspection.HIVE_ID_COLUMN, hiveInspection.getHiveId()),
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
						new IntegerSqlFunctionParameter(HiveInspection.WIND_SPEED_MPH_COLUMN, hiveInspection.getWindSpeedMph())),
			loginDao.getUserId());
	}

	@Override
	public HiveInspection read(int id)
	{
		return this.executeRead(
				"read_hive_inspection",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(HiveInspection.ID_COLUMN, id)));
	}

	@Override
	public List<HiveInspection> readList(int parentId)
	{
		return this.executeReadList(
				"read_hive_inspections",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(HiveInspection.HIVE_ID_COLUMN, parentId)));
	}

	@Override
	public void update(int id, HiveInspection hiveInspection)
	{
		this.executeUpdate(
				"update_hive_inspection",
				Arrays.asList(
						new IntegerSqlFunctionParameter(HiveInspection.ID_COLUMN, id),
						new BooleanSqlFunctionParameter(HiveInspection.QUEEN_SEEN_COLUMN, hiveInspection.getQueenSeen()),
						new BooleanSqlFunctionParameter(HiveInspection.EGGS_SEEN_COLUMN, hiveInspection.getEggsSeen()),
						new IntegerSqlFunctionParameter(HiveInspection.LAYING_PATTERN_STARS_COLUMN, hiveInspection.getLayingPatternStars()),
						new IntegerSqlFunctionParameter(HiveInspection.TEMPERAMENT_STARS_COLUMN, hiveInspection.getTemperamentStars()),
						new IntegerSqlFunctionParameter(HiveInspection.QUEEN_CELLS_COLUMN, hiveInspection.getQueenCells()),
						new IntegerSqlFunctionParameter(HiveInspection.SUPERSEDURE_CELLS_COLUMN, hiveInspection.getSupersedureCells()),
						new IntegerSqlFunctionParameter(HiveInspection.SWARM_CELLS_COLUMN, hiveInspection.getSwarmCells()),
						new IntegerSqlFunctionParameter(HiveInspection.COMB_BUILDING_STARS_COLUMN, hiveInspection.getCombBuildingStars()),
						new IntegerSqlFunctionParameter(HiveInspection.FRAMES_SEALED_BROOD_COLUMN, hiveInspection.getFramesOpenBrood()),
						new IntegerSqlFunctionParameter(HiveInspection.FRAMES_OPEN_BROOD_COLUMN, hiveInspection.getFramesOpenBrood()),
						new IntegerSqlFunctionParameter(HiveInspection.FRAMES_HONEY_COLUMN, hiveInspection.getFramesHoney()),
						new StringSqlFunctionParameter(HiveInspection.WEATHER_COLUMN, hiveInspection.getWeather()),
						new IntegerSqlFunctionParameter(HiveInspection.TEMPERATURE_F_COLUMN, hiveInspection.getTemperatureF()),
						new IntegerSqlFunctionParameter(HiveInspection.WIND_SPEED_MPH_COLUMN, hiveInspection.getWindSpeedMph())),
				loginDao.getUserId());
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_hive_inspection",
				Collections.singletonList(
						new IntegerSqlFunctionParameter(HiveInspection.ID_COLUMN, id)),
				loginDao.getUserId());
	}
	
	@Override
	public boolean canDelete(int id)
	{
		return true;
	}

	@Override
	public HiveInspection mapRow(ResultSet rs) throws SQLException
	{
		return new HiveInspection()
				.setId(rs.getInt(HiveInspection.ID_COLUMN))
				.setHiveId(rs.getInt(HiveInspection.HIVE_ID_COLUMN))
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
