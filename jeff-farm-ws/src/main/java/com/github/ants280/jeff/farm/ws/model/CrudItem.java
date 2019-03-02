package com.github.ants280.jeff.farm.ws.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.TimeZone;

public abstract class CrudItem
{
	private static final DateTimeFormatter DATE_FORMAT
			 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
					 .withLocale(Locale.getDefault())
					 .withZone(TimeZone.getDefault().toZoneId());
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	private final int id;
	private final String createdDate;
	private final String modifiedDate;

	public CrudItem(int id, Timestamp createdDate, Timestamp modifiedDate)
	{
		this.id = id;
		this.createdDate = getFormatedTimestamp(createdDate);
		this.modifiedDate = getFormatedTimestamp(modifiedDate);
	}

	private static String getFormatedTimestamp(Timestamp modifiedDate1)
	{
		return modifiedDate1 == null
				? null
				: DATE_FORMAT.format(modifiedDate1.toInstant());
	}

	public int getId()
	{
		return id;
	}

	@JsonProperty(access = Access.READ_ONLY)
	public String getCreatedDate()
	{
		return createdDate;
	}

	@JsonProperty(access = Access.READ_ONLY)
	public String getModifiedDate()
	{
		return modifiedDate;
	}
}
