package com.github.ants280.jeff.farm.ws.model;

public class HiveInspection extends CrudItemInspection<Hive, HiveInspection>
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String GROUP_ID_COLUMN = "group_id";
	public static final String TARGET_ID_COLUMN = "target_id";
	public static final String TARGET_NAME_COLUMN = "target_name";
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
	
	@Override
	protected HiveInspection getThis()
	{
		return this;
	}

	public boolean getQueenSeen()
	{
		return queenSeen;
	}

	public HiveInspection setQueenSeen(Boolean queenSeen)
	{
		this.queenSeen = Boolean.TRUE.equals(queenSeen);
		return this;
	}

	public boolean getEggsSeen()
	{
		return eggsSeen;
	}

	public HiveInspection setEggsSeen(Boolean eggsSeen)
	{
		this.eggsSeen = Boolean.TRUE.equals(eggsSeen);
		return this;
	}

	public int getLayingPatternStars()
	{
		return layingPatternStars;
	}

	public HiveInspection setLayingPatternStars(int layingPatternStars)
	{
		this.layingPatternStars = layingPatternStars;
		return this;
	}

	public int getTemperamentStars()
	{
		return temperamentStars;
	}

	public HiveInspection setTemperamentStars(int temperamentStars)
	{
		this.temperamentStars = temperamentStars;
		return this;
	}

	public int getQueenCells()
	{
		return queenCells;
	}

	public HiveInspection setQueenCells(int queenCells)
	{
		this.queenCells = queenCells;
		return this;
	}

	public int getSupersedureCells()
	{
		return supersedureCells;
	}

	public HiveInspection setSupersedureCells(int supersedureCells)
	{
		this.supersedureCells = supersedureCells;
		return this;
	}

	public int getSwarmCells()
	{
		return swarmCells;
	}

	public HiveInspection setSwarmCells(int swarmCells)
	{
		this.swarmCells = swarmCells;
		return this;
	}

	public int getCombBuildingStars()
	{
		return combBuildingStars;
	}

	public HiveInspection setCombBuildingStars(int combBuildingStars)
	{
		this.combBuildingStars = combBuildingStars;
		return this;
	}

	public int getFramesSealedBrood()
	{
		return framesSealedBrood;
	}

	public HiveInspection setFramesSealedBrood(int framesSealedBrood)
	{
		this.framesSealedBrood = framesSealedBrood;
		return this;
	}

	public int getFramesOpenBrood()
	{
		return framesOpenBrood;
	}

	public HiveInspection setFramesOpenBrood(int framesOpenBrood)
	{
		this.framesOpenBrood = framesOpenBrood;
		return this;
	}

	public int getFramesHoney()
	{
		return framesHoney;
	}

	public HiveInspection setFramesHoney(int framesHoney)
	{
		this.framesHoney = framesHoney;
		return this;
	}

	public String getWeather()
	{
		return weather;
	}

	public HiveInspection setWeather(String weather)
	{
		this.weather = weather;
		return this;
	}

	public int getTemperatureF()
	{
		return temperatureF;
	}

	public HiveInspection setTemperatureF(int temperatureF)
	{
		this.temperatureF = temperatureF;
		return this;
	}

	public int getWindSpeedMph()
	{
		return windSpeedMph;
	}

	public HiveInspection setWindSpeedMph(int windSpeedMph)
	{
		this.windSpeedMph = windSpeedMph;
		return this;
	}
}
