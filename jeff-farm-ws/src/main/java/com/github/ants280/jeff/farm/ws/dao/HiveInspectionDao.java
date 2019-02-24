package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.StoredProcedureDao.Parameter;
import com.github.ants280.jeff.farm.ws.model.Hive;
import com.github.ants280.jeff.farm.ws.model.HiveInspection;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;

@Service
public class HiveInspectionDao extends StoredProcedureDao implements CrudDao<HiveInspection>
{
	private final HiveDao hiveDao; // TODO: should not be needed.

	@Inject
	public HiveInspectionDao(DataSource dataSource, HiveDao hiveDao)
	{
		super(dataSource);
		this.hiveDao = hiveDao;
	}

	@Override
	public int create(HiveInspection hiveInspection)
	{
		List<Hive> hives = hiveDao.read();
		Hive hive = hives.stream()
				.filter(hive2 -> hive2.getId() == hiveInspection.getHiveId())
				.findFirst()
				.orElseThrow(() -> new AssertionError("No Hive found for id #" + hiveInspection.getHiveId()));

		return this.executeCreate(
				"create_hive_inspection",
				Arrays.asList(
						new Parameter(HiveInspection.HIVE_ID_COLUMN, hiveInspection.getHiveId(), Types.INTEGER),
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER),
						new Parameter(HiveInspection.QUEEN_SEEN_COLUMN, hiveInspection.isQueenSeen(), Types.BIT), // todo: rename to getQueenSeen
						new Parameter(HiveInspection.EGGS_SEEN_COLUMN, hiveInspection.isEggsSeen(), Types.BIT),
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
	public List<HiveInspection> read()
	{
		return this.executeRead(
				"read_hive_inspections",
				Collections.emptyList(),
				new HiveInspection.ResultSetExtractor());
	}

	@Override
	public void update(HiveInspection hiveInspection)
	{
		List<Hive> hives = hiveDao.read();
		Hive hive = hives.stream()
				.filter(hive2 -> hive2.getId() == hiveInspection.getHiveId())
				.findFirst()
				.orElseThrow(() -> new AssertionError("No Hive found for id #" + hiveInspection.getHiveId()));

		this.executeUpdate(
				"update_hive_inspection",
				Arrays.asList(
						new Parameter(HiveInspection.ID_COLUMN, hiveInspection.getId(), Types.INTEGER),
						new Parameter(HiveInspection.HIVE_ID_COLUMN, hiveInspection.getHiveId(), Types.INTEGER),
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER),
						new Parameter(HiveInspection.QUEEN_SEEN_COLUMN, hiveInspection.isQueenSeen(), Types.BIT), // todo: rename to getQueenSeen
						new Parameter(HiveInspection.EGGS_SEEN_COLUMN, hiveInspection.isEggsSeen(), Types.BIT),
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
		List<HiveInspection> hiveInspections = this.read();
		HiveInspection hiveInspection = hiveInspections.stream()
				.filter(hiveInspection2 -> hiveInspection2.getId() == id)
				.findFirst()
				.orElseThrow(() -> new AssertionError("No HiveInspection found for hiveInspection #" + id));
		List<Hive> hives = hiveDao.read();
		Hive hive = hives.stream()
				.filter(hive2 -> hive2.getId() == hiveInspection.getHiveId())
				.findFirst()
				.orElseThrow(() -> new AssertionError("No Hive found for id #" + hiveInspection.getHiveId()));

		this.executeUpdate(
				"delete_hive_inspection",
				Arrays.asList(
						new Parameter(HiveInspection.ID_COLUMN, id, Types.INTEGER),
						new Parameter(HiveInspection.HIVE_ID_COLUMN, hiveInspection.getHiveId(), Types.INTEGER),
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER)));
	}
}
