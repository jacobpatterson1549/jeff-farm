package com.github.ants280.jeff.farm.ws.model;

public class HiveInspection
{
	public static final String ID_COLUMN = "id";
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
	private int id;
	private int hiveId;
	private boolean queenSeen;
	private boolean eggsSeen;
	private int layingPatternStars;
	private int temperamentStars;
	private int queenCells;
	private int supersedureCells;
	private int swarmCells;
	private int combBuildingStars;
	private int framesSealedBrood;
	private int framesOpenBrood;
	private int framesHoney;
	private String weather;
	private int temperatureF;
	private int windSpeedMph;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getHiveId()
	{
		return hiveId;
	}

	public void setHiveId(int hiveId)
	{
		this.hiveId = hiveId;
	}

	public boolean getQueenSeen()
	{
		return queenSeen;
	}

	public void setQueenSeen(boolean queenSeen)
	{
		this.queenSeen = queenSeen;
	}

	public boolean getEggsSeen()
	{
		return eggsSeen;
	}

	public void setEggsSeen(boolean eggsSeen)
	{
		this.eggsSeen = eggsSeen;
	}

	public int getLayingPatternStars()
	{
		return layingPatternStars;
	}

	public void setLayingPatternStars(int layingPatternStars)
	{
		this.layingPatternStars = layingPatternStars;
	}

	public int getTemperamentStars()
	{
		return temperamentStars;
	}

	public void setTemperamentStars(int temperamentStars)
	{
		this.temperamentStars = temperamentStars;
	}

	public int getQueenCells()
	{
		return queenCells;
	}

	public void setQueenCells(int queenCells)
	{
		this.queenCells = queenCells;
	}

	public int getSupersedureCells()
	{
		return supersedureCells;
	}

	public void setSupersedureCells(int supersedureCells)
	{
		this.supersedureCells = supersedureCells;
	}

	public int getSwarmCells()
	{
		return swarmCells;
	}

	public void setSwarmCells(int swarmCells)
	{
		this.swarmCells = swarmCells;
	}

	public int getCombBuildingStars()
	{
		return combBuildingStars;
	}

	public void setCombBuildingStars(int combBuildingStars)
	{
		this.combBuildingStars = combBuildingStars;
	}

	public int getFramesSealedBrood()
	{
		return framesSealedBrood;
	}

	public void setFramesSealedBrood(int framesSealedBrood)
	{
		this.framesSealedBrood = framesSealedBrood;
	}

	public int getFramesOpenBrood()
	{
		return framesOpenBrood;
	}

	public void setFramesOpenBrood(int framesOpenBrood)
	{
		this.framesOpenBrood = framesOpenBrood;
	}

	public int getFramesHoney()
	{
		return framesHoney;
	}

	public void setFramesHoney(int framesHoney)
	{
		this.framesHoney = framesHoney;
	}

	public String getWeather()
	{
		return weather;
	}

	public void setWeather(String weather)
	{
		this.weather = weather;
	}

	public int getTemperatureF()
	{
		return temperatureF;
	}

	public void setTemperatureF(int temperatureF)
	{
		this.temperatureF = temperatureF;
	}

	public int getWindSpeedMph()
	{
		return windSpeedMph;
	}

	public void setWindSpeedMph(int windSpeedMph)
	{
		this.windSpeedMph = windSpeedMph;
	}
}
