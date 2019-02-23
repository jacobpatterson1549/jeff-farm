package com.github.ants280.jeff.farm.ws.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class QueenBee implements Serializable
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

	public static class ResultSetExtractor implements RowMapper<QueenBee>
	{
		@Override
		public QueenBee mapRow(ResultSet rs, int i) throws SQLException
		{
			QueenBee queenBee = new QueenBee();
			queenBee.setId(rs.getInt(ID_COLUMN));
			queenBee.setHiveId(rs.getInt(HIVE_ID_COLUMN));
			queenBee.setMarkColor(rs.getString(MARK_COLOR_COLUMN));
			return queenBee;
		}
	}
}
