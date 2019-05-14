package com.github.ants280.jeff.farm.ws.model;

public class Poultry extends CrudItem<Poultry>
{
	public static final String FARM_ID_COLUMN = "farm_id";
	public static final String NAME_COLUMN = "name";
	private int farmId;
	private String name;

	@Override
	protected Poultry getThis()
	{
		return this;
	}

	public int getFarmId()
	{
		return farmId;
	}

	public Poultry setFarmId(int farmId)
	{
		this.farmId = farmId;
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Poultry setName(String name)
	{
		this.name = name;
		return this;
	}
}
