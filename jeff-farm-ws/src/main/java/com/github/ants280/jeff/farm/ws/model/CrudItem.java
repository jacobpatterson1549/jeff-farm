package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.TimeZone;
import javax.json.bind.annotation.JsonbTransient;

public abstract class CrudItem<T extends CrudItem>
{
	private static final DateTimeFormatter DATE_FORMAT
			 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
					 .withLocale(Locale.getDefault())
					 .withZone(TimeZone.getDefault().toZoneId());
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String USER_ID = "user_id";
	public static final String CAN_DELETE_ITEM = "can_delete";
	private int id;
	private String createdDate;
	private String modifiedDate;

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

	public T setId(int id)
	{
		this.id = id;
		return (T) this;
	}

	public String getCreatedDate()
	{
		return createdDate;
	}

	public T setCreatedDate(String createdDate)
	{
		this.createdDate = createdDate;
		return (T) this;
	}

	public T setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = getFormatedTimestamp(createdDate);
		return (T) this;
	}

	public String getModifiedDate()
	{
		return modifiedDate;
	}

	public T setModifiedDate(String modifiedDate)
	{
		this.modifiedDate = modifiedDate;
		return (T) this;
	}

	@JsonbTransient
	public T setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = getFormatedTimestamp(modifiedDate);
		return (T) this;
	}
}
