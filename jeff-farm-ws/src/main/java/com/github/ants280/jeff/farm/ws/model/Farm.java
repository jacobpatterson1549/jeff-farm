package com.github.ants280.jeff.farm.ws.model;

public class Farm extends CrudItem<Farm>
{
	public static final String NAME_COLUMN = "name";
	public static final String LOCATION_COLUMN = "location";
	private String name;
	private String location;
	
	@Override
	protected Farm getThis()
	{
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Farm setName(String name)
	{
		this.name = name;
		return this;
	}

	public String getLocation()
	{
		return location;
	}

	public Farm setLocation(String location)
	{
		this.location = location;
		return this;
	}

	
}
