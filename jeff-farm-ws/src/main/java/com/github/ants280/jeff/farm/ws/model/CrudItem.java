package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;

public abstract class CrudItem
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	private final int id;
	private final Timestamp createdDate;
	private final Timestamp modifiedDate;

	public CrudItem(int id, Timestamp createdDate, Timestamp modifiedDate)
	{
		this.id = id;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public int getId()
	{
		return id;
	}

	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}
}
