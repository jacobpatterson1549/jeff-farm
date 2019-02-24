package com.github.ants280.jeff.farm.ws.model;

public class Farm
{
	public static final String ID_COLUMN = "id";
	public static final String NAME_COLUMN = "name";
	public static final String LOCATION_COLUMN = "location";
	private int id;
	private String name;
	private String location;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}
}
