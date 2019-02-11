package com.github.ants280.jeff.farm.ws.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Farm implements Serializable
{
	@Id
	private int id;
	
	private String name;
	
	private String location;
	
	private Timestamp createdDate;
	
	private Timestamp modifiedDate;
	
	private boolean active;

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

	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	
}
