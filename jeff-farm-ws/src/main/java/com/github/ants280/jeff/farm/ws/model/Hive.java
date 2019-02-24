package com.github.ants280.jeff.farm.ws.model;

public class Hive
{
	public static final String ID_COLUMN = "id";
	public static final String FARM_ID_COLUMN = "farm_id";
	public static final String NAME_COLUMN = "name";
	private int id;
	private int farmId;
	private String name;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getFarmId()
	{
		return farmId;
	}

	public void setFarmId(int farmId)
	{
		this.farmId = farmId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
