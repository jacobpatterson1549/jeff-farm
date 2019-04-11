package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.annotation.JsonbTransient;

public class Hive extends CrudItem<Hive>
{
	public static final String FARM_ID_COLUMN = "farm_id";
	public static final String NAME_COLUMN = "name";
	public static final String QUEEN_COLOR_COLUMN = "queen_color";
	private int farmId;
	private String name;
	private String queenColor;

	public int getFarmId()
	{
		return farmId;
	}

	public Hive setFarmId(int farmId)
	{
		this.farmId = farmId;
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Hive setName(String name)
	{
		this.name = name;
		return this;
	}

	public String getQueenColor()
	{
		return queenColor;
	}

	@JsonbTransient
	public int getQueenColorInteger()
	{
		return Integer.parseInt(queenColor.substring(1), 16);
	}

	public Hive setQueenColor(String queenColor)
	{
		if (queenColor == null || !queenColor.matches("^#[0-9a-fA-F]{6}$"))
		{
			throw new IllegalArgumentException("Invalid color: " + queenColor);
		}
		this.queenColor = queenColor;
		return this;
	}

	@JsonbTransient
	public Hive setQueenColorInteger(int queenColor)
	{
		return this.setQueenColor(String.format("#%06x", queenColor));
	}
}