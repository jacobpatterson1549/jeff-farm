package com.github.ants280.jeff.farm.ws.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class Farm implements Serializable
{
	private int id;
	
	private String name;
	
	private String location;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public static class ResultSetExtractor implements RowMapper<Farm>
	{
		@Override
		public Farm mapRow(ResultSet rs, int i) throws SQLException
		{
			Farm farm = new Farm();
			farm.setId(rs.getInt("ID"));
			farm.setName(rs.getString("name"));
			farm.setLocation(rs.getString("location"));
			return farm;
		}
	}
}
