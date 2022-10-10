package com.github.ants280.jeff.farm.ws.model;

public class CrudItemCoordinate extends CrudItem<CrudItemCoordinate>
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String MAP_ID_COLUMN = "map_id"; // parent id
	public static final String LATITUDE_COLUMN = "latitude";
	public static final String LONGITUDE_COLUMN = "longitude";
	public static final String DISPLAY_ORDER_COLUMN = "display_order";
	private double latitude;
	private double longitude;
	private int displayOrder;

	@Override
	protected CrudItemCoordinate getThis()
	{
		return this;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public CrudItemCoordinate setLatitude(double latitude)
	{
		this.latitude = latitude;
		return this;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public CrudItemCoordinate setLongitude(double longitude)
	{
		this.longitude = longitude;
		return this;
	}

	public int getDisplayOrder()
	{
		return displayOrder;
	}

	public CrudItemCoordinate setDisplayOrder(int displayOrder)
	{
		this.displayOrder = displayOrder;
		return this;
	}
}
