package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.TimeZone;
import javax.json.bind.annotation.JsonbTransient;

public abstract class CrudItem<T extends CrudItem<T>>
{
	private static final DateTimeFormatter DATE_FORMAT
		= DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
		.withLocale(Locale.getDefault())
		.withZone(TimeZone.getDefault().toZoneId());
	private Integer id;
	private Integer parentId;
	private String createdDate;
	private String modifiedDate;

	private static String getFormattedTimestamp(Timestamp timestamp)
	{
		return timestamp == null
			? null
			: DATE_FORMAT.format(timestamp.toInstant());
	}

	protected abstract T getThis();

	public Integer getId()
	{
		return id;
	}

	public T setId(Integer id)
	{
		this.id = id;
		return getThis();
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public T setParentId(Integer parentId)
	{
		this.parentId = parentId;
		return getThis();
	}

	public String getCreatedDate()
	{
		return createdDate;
	}

	@JsonbTransient
	public T setCreatedTimestamp(Timestamp createdDate)
	{
		this.createdDate = getFormattedTimestamp(createdDate);
		return getThis();
	}

	public String getModifiedDate()
	{
		return modifiedDate;
	}

	@JsonbTransient
	public T setModifiedTimestamp(Timestamp modifiedDate)
	{
		this.modifiedDate = getFormattedTimestamp(modifiedDate);
		return getThis();
	}
}
