package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;

public class Hive extends CrudItem
{
	public static final String FARM_ID_COLUMN = "farm_id";
	public static final String NAME_COLUMN = "name";
	private final int farmId;
	private final String name;

	public Hive(
			int id,
			int farmId,
			String name,
			Timestamp createdDate,
			Timestamp modifiedDate)
	{
		super(id, createdDate, modifiedDate);
		this.farmId = farmId;
		this.name = name;
	}
	

	public int getFarmId()
	{
		return farmId;
	}

	public String getName()
	{
		return name;
	}
}
