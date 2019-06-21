package com.github.ants280.jeff.farm.ws.model;

public class FarmPermission extends CrudItem<FarmPermission>
{
	public static final String FARM_ID_COLUMN = "farm_id";
	public static final String USER_NAME_COLUMN = "user_name";
	public static final String USER_ID_COLUMN = "permission_user_id";
	private int farmId;
	private String userName;

	@Override
	protected FarmPermission getThis()
	{
		return this;
	}

	public int getFarmId()
	{
		return farmId;
	}

	public FarmPermission setFarmId(int farmId)
	{
		this.farmId = farmId;
		return this;
	}

	public String getUserName()
	{
		return userName;
	}

	public FarmPermission setUserName(String userName)
	{
		this.userName = userName;
		return this;
	}
}
