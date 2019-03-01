package com.github.ants280.jeff.farm.ws.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;

public class Hive extends CrudItem
{
	public static final String FARM_ID_COLUMN = "farm_id";
	public static final String NAME_COLUMN = "name";
	public static final String QUEEN_COLOR_COLUMN = "queen_color";
	private final int farmId;
	private final String name;
	private final int queenColor;

	public Hive(
			int id,
			int farmId,
			String name,
			int queenColor,
			Timestamp createdDate,
			Timestamp modifiedDate)
	{
		super(id, createdDate, modifiedDate);
		this.farmId = farmId;
		this.name = name;
		this.queenColor = queenColor;
	}
	
	@JsonCreator
	private Hive(
			int id,
			int farmId,
			String name,
			String queenColor,
			Timestamp createdDate,
			Timestamp modifiedDate)
	{
		this(id,
				farmId,
				name,
				createHexColor(queenColor),
				createdDate,
				modifiedDate);
	}

	private static int createHexColor(String queenColor)
	{
		if (queenColor == null || !queenColor.matches("^#?[0-9a-fA-F]{6}$"))
		{
			throw new IllegalArgumentException("Invalid color: " + queenColor);
		}
		return Integer.parseInt(queenColor.replaceAll("^#", ""), 16);
	}

	public int getFarmId()
	{
		return farmId;
	}

	public String getName()
	{
		return name;
	}

	@JsonIgnore // used for dao
	public int getQueenColorInteger()
	{
		return queenColor;
	}

	public String getQueenColor()
	{
		return String.format("%06x", queenColor);
	}
}