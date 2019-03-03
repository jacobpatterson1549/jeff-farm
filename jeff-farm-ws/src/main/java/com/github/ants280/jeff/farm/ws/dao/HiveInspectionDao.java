package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.HiveInspection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;
import org.springframework.jdbc.core.RowMapper;

@Service
public class HiveInspectionDao extends StoredProcedureDao implements CrudDao<HiveInspection>
{
	@Inject
	public HiveInspectionDao(DataSource dataSource)
	{
		super(dataSource);
	}

	@Override
	public int create(HiveInspection hiveInspection)
	{
		return this.executeCreate(
				"create_hive_inspection",
				Arrays.asList(
						new Parameter(HiveInspection.HIVE_ID_COLUMN, hiveInspection.getHiveId(), Types.INTEGER),
						new Parameter(HiveInspection.QUEEN_SEEN_COLUMN, hiveInspection.getQueenSeen(), Types.BIT),
						new Parameter(HiveInspection.EGGS_SEEN_COLUMN, hiveInspection.getEggsSeen(), Types.BIT),
						new Parameter(HiveInspection.LAYING_PATTERN_STARS_COLUMN, hiveInspection.getLayingPatternStars(), Types.INTEGER),
						new Parameter(HiveInspection.TEMPERAMENT_STARS_COLUMN, hiveInspection.getTemperamentStars(), Types.INTEGER),
						new Parameter(HiveInspection.QUEEN_CELLS_COLUMN, hiveInspection.getQueenCells(), Types.INTEGER),
						new Parameter(HiveInspection.SUPERSEDURE_CELLS_COLUMN, hiveInspection.getSupersedureCells(), Types.INTEGER),
						new Parameter(HiveInspection.SWARM_CELLS_COLUMN, hiveInspection.getSwarmCells(), Types.INTEGER),
						new Parameter(HiveInspection.COMB_BUILDING_STARS_COLUMN, hiveInspection.getCombBuildingStars(), Types.INTEGER),
						new Parameter(HiveInspection.FRAMES_SEALED_BROOD_COLUMN, hiveInspection.getFramesOpenBrood(), Types.INTEGER),
						new Parameter(HiveInspection.FRAMES_OPEN_BROOD_COLUMN, hiveInspection.getFramesOpenBrood(), Types.INTEGER),
						new Parameter(HiveInspection.FRAMES_HONEY_COLUMN, hiveInspection.getFramesHoney(), Types.INTEGER),
						new Parameter(HiveInspection.WEATHER_COLUMN, hiveInspection.getWeather(), Types.VARCHAR),
						new Parameter(HiveInspection.TEMPERATURE_F_COLUMN, hiveInspection.getTemperatureF(), Types.INTEGER),
						new Parameter(HiveInspection.WIND_SPEED_MPH_COLUMN, hiveInspection.getWindSpeedMph(), Types.INTEGER)),
				HiveInspection.ID_COLUMN);
	}

	@Override
	public HiveInspection read(int id)
	{
		return this.executeRead(
				"read_hive_inspection",
				Collections.singletonList(
						new Parameter(HiveInspection.ID_COLUMN, id, Types.INTEGER)),
				new ResultSetExtractor());
	}

	@Override
	public List<HiveInspection> readList(int parentId)
	{
		return this.executeReadList(
				"read_hive_inspections",
				Collections.singletonList(
						new Parameter(HiveInspection.HIVE_ID_COLUMN, parentId, Types.INTEGER)),
				new ResultSetExtractor());
	}

	@Override
	public void update(HiveInspection hiveInspection)
	{
		this.executeUpdate(
				"update_hive_inspection",
				Arrays.asList(
						new Parameter(HiveInspection.ID_COLUMN, hiveInspection.getId(), Types.INTEGER),
						new Parameter(HiveInspection.HIVE_ID_COLUMN, hiveInspection.getHiveId(), Types.INTEGER),
						new Parameter(HiveInspection.QUEEN_SEEN_COLUMN, hiveInspection.getQueenSeen(), Types.BIT),
						new Parameter(HiveInspection.EGGS_SEEN_COLUMN, hiveInspection.getEggsSeen(), Types.BIT),
						new Parameter(HiveInspection.LAYING_PATTERN_STARS_COLUMN, hiveInspection.getLayingPatternStars(), Types.INTEGER),
						new Parameter(HiveInspection.TEMPERAMENT_STARS_COLUMN, hiveInspection.getTemperamentStars(), Types.INTEGER),
						new Parameter(HiveInspection.QUEEN_CELLS_COLUMN, hiveInspection.getQueenCells(), Types.INTEGER),
						new Parameter(HiveInspection.SUPERSEDURE_CELLS_COLUMN, hiveInspection.getSupersedureCells(), Types.INTEGER),
						new Parameter(HiveInspection.SWARM_CELLS_COLUMN, hiveInspection.getSwarmCells(), Types.INTEGER),
						new Parameter(HiveInspection.COMB_BUILDING_STARS_COLUMN, hiveInspection.getCombBuildingStars(), Types.INTEGER),
						new Parameter(HiveInspection.FRAMES_SEALED_BROOD_COLUMN, hiveInspection.getFramesOpenBrood(), Types.INTEGER),
						new Parameter(HiveInspection.FRAMES_OPEN_BROOD_COLUMN, hiveInspection.getFramesOpenBrood(), Types.INTEGER),
						new Parameter(HiveInspection.FRAMES_HONEY_COLUMN, hiveInspection.getFramesHoney(), Types.INTEGER),
						new Parameter(HiveInspection.WEATHER_COLUMN, hiveInspection.getWeather(), Types.VARCHAR),
						new Parameter(HiveInspection.TEMPERATURE_F_COLUMN, hiveInspection.getTemperatureF(), Types.INTEGER),
						new Parameter(HiveInspection.WIND_SPEED_MPH_COLUMN, hiveInspection.getWindSpeedMph(), Types.INTEGER)));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_hive_inspection",
				Collections.singletonList(
						new Parameter(HiveInspection.ID_COLUMN, id, Types.INTEGER)));
	}
	
	@Override
	public boolean canDelete(int id)
	{
		return true;
	}

	private static class ResultSetExtractor implements RowMapper<HiveInspection>
	{
		@Override
		public HiveInspection mapRow(ResultSet rs, int i) throws SQLException
		{
			return new HiveInspection(
					rs.getInt(HiveInspection.ID_COLUMN),
					rs.getInt(HiveInspection.HIVE_ID_COLUMN),
					rs.getBoolean(HiveInspection.QUEEN_SEEN_COLUMN),
					rs.getBoolean(HiveInspection.EGGS_SEEN_COLUMN),
					rs.getInt(HiveInspection.LAYING_PATTERN_STARS_COLUMN),
					rs.getInt(HiveInspection.TEMPERAMENT_STARS_COLUMN),
					rs.getInt(HiveInspection.QUEEN_CELLS_COLUMN),
					rs.getInt(HiveInspection.SUPERSEDURE_CELLS_COLUMN),
					rs.getInt(HiveInspection.SWARM_CELLS_COLUMN),
					rs.getInt(HiveInspection.COMB_BUILDING_STARS_COLUMN),
					rs.getInt(HiveInspection.FRAMES_SEALED_BROOD_COLUMN),
					rs.getInt(HiveInspection.FRAMES_OPEN_BROOD_COLUMN),
					rs.getInt(HiveInspection.FRAMES_HONEY_COLUMN),
					rs.getString(HiveInspection.WEATHER_COLUMN),
					rs.getInt(HiveInspection.TEMPERATURE_F_COLUMN),
					rs.getInt(HiveInspection.WIND_SPEED_MPH_COLUMN),
					rs.getTimestamp(HiveInspection.CREATED_DATE_COLUMN),
					rs.getTimestamp(HiveInspection.MODIFIED_DATE_COLUMN));
		}
	}
}
