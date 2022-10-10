package com.github.ants280.jeff.farm.ws.model;

public class Livestock extends CrudItem<Livestock>
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String CAN_DELETE_ITEM = "can_delete";
	public static final String FARM_ID_COLUMN = "farm_id"; // parent column
	public static final String NAME_COLUMN = "name";
	private String name;

	@Override
	protected Livestock getThis()
	{
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Livestock setName(String name)
	{
		this.name = name;
		return this;
	}
}
