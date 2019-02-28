package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;

public class HiveInspection extends CrudItem
{
	public static final String HIVE_ID_COLUMN = "hive_id";
	public static final String QUEEN_SEEN_COLUMN = "queen_seen";
	public static final String EGGS_SEEN_COLUMN = "eggs_seen";
	public static final String LAYING_PATTERN_STARS_COLUMN = "laying_pattern_stars";
	public static final String TEMPERAMENT_STARS_COLUMN = "temperament_stars";
	public static final String QUEEN_CELLS_COLUMN = "queen_cells";
	public static final String SUPERSEDURE_CELLS_COLUMN = "supersedure_cells";
	public static final String SWARM_CELLS_COLUMN = "swarm_cells";
	public static final String COMB_BUILDING_STARS_COLUMN = "comb_building_stars";
	public static final String FRAMES_SEALED_BROOD_COLUMN = "frames_sealed_brood";
	public static final String FRAMES_OPEN_BROOD_COLUMN = "frames_open_brood";
	public static final String FRAMES_HONEY_COLUMN = "frames_honey";
	public static final String WEATHER_COLUMN = "weather";
	public static final String TEMPERATURE_F_COLUMN = "temperature_f";
	public static final String WIND_SPEED_MPH_COLUMN = "wind_speed_mph";
	private final int hiveId;
	private final boolean queenSeen;
	private final boolean eggsSeen;
	private final int layingPatternStars;
	private final int temperamentStars;
	private final int queenCells;
	private final int supersedureCells;
	private final int swarmCells;
	private final int combBuildingStars;
	private final int framesSealedBrood;
	private final int framesOpenBrood;
	private final int framesHoney;
	private final String weather;
	private final int temperatureF;
	private final int windSpeedMph;

	public HiveInspection(
			int id,
			int hiveId,
			boolean queenSeen,
			boolean eggsSeen,
			int layingPatternStars,
			int temperamentStars,
			int queenCells,
			int supersedureCells,
			int swarmCells,
			int combBuildingStars,
			int framesSealedBrood,
			int framesOpenBrood,
			int framesHoney,
			String weather,
			int temperatureF,
			int windSpeedMph,
			Timestamp createdDate,
			Timestamp modifiedDate)
	{
		super(id, createdDate, modifiedDate);
		this.hiveId = hiveId;
		this.queenSeen = queenSeen;
		this.eggsSeen = eggsSeen;
		this.layingPatternStars = layingPatternStars;
		this.temperamentStars = temperamentStars;
		this.queenCells = queenCells;
		this.supersedureCells = supersedureCells;
		this.swarmCells = swarmCells;
		this.combBuildingStars = combBuildingStars;
		this.framesSealedBrood = framesSealedBrood;
		this.framesOpenBrood = framesOpenBrood;
		this.framesHoney = framesHoney;
		this.weather = weather;
		this.temperatureF = temperatureF;
		this.windSpeedMph = windSpeedMph;
	}

	@Override
	public String getDisplayValue()
	{
		return this.getCreatedDate();
	}

	public int getHiveId()
	{
		return hiveId;
	}

	public boolean getQueenSeen()
	{
		return queenSeen;
	}

	public boolean getEggsSeen()
	{
		return eggsSeen;
	}

	public int getLayingPatternStars()
	{
		return layingPatternStars;
	}

	public int getTemperamentStars()
	{
		return temperamentStars;
	}

	public int getQueenCells()
	{
		return queenCells;
	}

	public int getSupersedureCells()
	{
		return supersedureCells;
	}

	public int getSwarmCells()
	{
		return swarmCells;
	}

	public int getCombBuildingStars()
	{
		return combBuildingStars;
	}

	public int getFramesSealedBrood()
	{
		return framesSealedBrood;
	}

	public int getFramesOpenBrood()
	{
		return framesOpenBrood;
	}

	public int getFramesHoney()
	{
		return framesHoney;
	}

	public String getWeather()
	{
		return weather;
	}

	public int getTemperatureF()
	{
		return temperatureF;
	}

	public int getWindSpeedMph()
	{
		return windSpeedMph;
	}
	
	
}
