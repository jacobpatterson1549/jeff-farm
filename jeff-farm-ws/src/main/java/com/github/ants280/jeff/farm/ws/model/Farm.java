package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;

public class Farm extends CrudItem
{
	public static final String NAME_COLUMN = "name";
	public static final String LOCATION_COLUMN = "location";
	private final String name;
	private final String location;

	public Farm(
			int id,
			String name,
			String location,
			Timestamp createdDate,
			Timestamp modifiedDate)
	{
		super(id, createdDate, modifiedDate);
		this.name = name;
		this.location = location;
	}
	

	public String getName()
	{
		return name;
	}

	public String getLocation()
	{
		return location;
	}
}
