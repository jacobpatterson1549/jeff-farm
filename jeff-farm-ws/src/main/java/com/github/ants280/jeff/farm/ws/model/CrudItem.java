package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class CrudItem
{
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
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

	public abstract String getDisplayValue();

	public int getId()
	{
		return id;
	}

	public String getCreatedDate()
	{
		return DATE_FORMAT.format(createdDate);
	}

	public String getModifiedDate()
	{
		return DATE_FORMAT.format(modifiedDate);
	}
}
