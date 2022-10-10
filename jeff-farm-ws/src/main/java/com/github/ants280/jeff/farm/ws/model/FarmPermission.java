package com.github.ants280.jeff.farm.ws.model;

public class FarmPermission extends CrudItem<FarmPermission>
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String CAN_DELETE_ITEM = "can_delete";
	public static final String FARM_ID_COLUMN = "farm_id"; // parent column
	public static final String USER_NAME_COLUMN = "user_name";
	public static final String USER_ID_COLUMN = "permission_user_id";
	private String userName;

	@Override
	protected FarmPermission getThis()
	{
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
