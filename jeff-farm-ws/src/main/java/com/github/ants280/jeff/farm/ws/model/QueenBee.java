package com.github.ants280.jeff.farm.ws.model;

public class QueenBee
{
	public static final String ID_COLUMN = "id";
	public static final String HIVE_ID_COLUMN = "hive_id";
	public static final String MARK_COLOR_COLUMN = "mark_color";
	private int id;
	private int hiveId;
	private String markColor;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getHiveId()
	{
		return hiveId;
	}

	public void setHiveId(int hiveId)
	{
		this.hiveId = hiveId;
	}

	public String getMarkColor()
	{
		return markColor;
	}

	public void setMarkColor(String markColor)
	{
		this.markColor = markColor;
	}
}
