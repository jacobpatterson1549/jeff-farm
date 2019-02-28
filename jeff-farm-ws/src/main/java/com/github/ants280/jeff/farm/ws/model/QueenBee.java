package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;

public class QueenBee extends CrudItem
{
	public static final String HIVE_ID_COLUMN = "hive_id";
	public static final String MARK_COLOR_COLUMN = "mark_color";
	private final int hiveId;
	private final String markColor;

	public QueenBee(
			int id,
			int hiveId,
			String markColor,
			Timestamp createdDate,
			Timestamp modifiedDate)
	{
		super(id, createdDate, modifiedDate);
		this.hiveId = hiveId;
		this.markColor = markColor;
	}
	
	public int getHiveId()
	{
		return hiveId;
	}

	public String getMarkColor()
	{
		return markColor;
	}
}
