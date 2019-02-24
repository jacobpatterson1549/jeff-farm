package com.github.ants280.jeff.farm.ws.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class Hive implements Serializable
{
	public static final String ID_COLUMN = "id";
	public static final String FARM_ID_COLUMN = "farm_id";
	public static final String NAME_COLUMN = "name";
	private int id;
	private int farmId;
	private String name;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getFarmId()
	{
		return farmId;
	}

	public void setFarmId(int farmId)
	{
		this.farmId = farmId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public static class ResultSetExtractor implements RowMapper<Hive>
	{
		@Override
		public Hive mapRow(ResultSet rs, int i) throws SQLException
		{
			Hive hive = new Hive();
			hive.setId(rs.getInt(ID_COLUMN));
			hive.setFarmId(rs.getInt(FARM_ID_COLUMN));
			hive.setName(rs.getString(NAME_COLUMN));
			return hive;
		}
	}
}
