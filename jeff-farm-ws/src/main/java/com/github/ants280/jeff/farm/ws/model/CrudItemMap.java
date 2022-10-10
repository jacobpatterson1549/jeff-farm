package com.github.ants280.jeff.farm.ws.model;

import java.util.List;

public class CrudItemMap extends CrudItem<CrudItemMap>
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String CAN_DELETE_ITEM = "can_delete";
	public static final String TARGET_ID_COLUMN = "target_id";
	public static final String TARGET_NAME_COLUMN = "target_name";
	private int targetId;
	private String targetName;
	private List<CrudItemCoordinate> coordinates;

	@Override
	protected CrudItemMap getThis()
	{
		return this;
	}

	public int getTargetId()
	{
		return targetId;
	}

	public CrudItemMap setTargetId(int targetId)
	{
		this.targetId = targetId;
		return this;
	}

	public String getTargetName()
	{
		return targetName;
	}

	public CrudItemMap setTargetName(String targetName)
	{
		this.targetName = targetName;
		return this;
	}

	public List<CrudItemCoordinate> getCoordinates()
	{
		return coordinates;
	}

	public CrudItemMap setCoordinates(List<CrudItemCoordinate> coordinates)
	{
		this.coordinates = coordinates;
		return this;
	}
}
