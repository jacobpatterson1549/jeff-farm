package com.github.ants280.jeff.farm.ws.model;

public class Cattle extends CrudItem<Cattle>
{
	public static final String FARM_ID_COLUMN = "farm_id"; // parent column
	public static final String NAME_COLUMN = "name";
	private String name;

	@Override
	protected Cattle getThis()
	{
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Cattle setName(String name)
	{
		this.name = name;
		return this;
	}
}
