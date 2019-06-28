package com.github.ants280.jeff.farm.ws.model;

public class CrudItemMapUpdate
{
	private CrudItemMap map;
	private CrudItemCoordinate[] addCoordinates;
	private int[] removeCoordinateIds;

	public CrudItemMap getMap()
	{
		return map;
	}

	public void setMap(CrudItemMap map)
	{
		this.map = map;
	}

	public CrudItemCoordinate[] getAddCoordinates()
	{
		return addCoordinates;
	}

	public void setAddCoordinates(CrudItemCoordinate[] addCoordinates)
	{
		this.addCoordinates = addCoordinates;
	}

	public int[] getRemoveCoordinateIds()
	{
		return removeCoordinateIds;
	}

	public void setRemoveCoordinateIds(int[] removeCoordinateIds)
	{
		this.removeCoordinateIds = removeCoordinateIds;
	}
}
