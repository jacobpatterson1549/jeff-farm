package com.github.ants280.jeff.farm.ws.model;

public class LoginSuccess
{
	private final boolean adminUser;
	private final int userId;
	private final String jsessionId;

	public LoginSuccess(boolean adminUser, int userId, String jsessionId)
	{
		this.adminUser = adminUser;
		this.userId = userId;
		this.jsessionId = jsessionId;
	}

	public boolean getAdminUser()
	{
		return adminUser;
	}

	public int getUserId()
	{
		return userId;
	}

	public String getJsessionId()
	{
		return jsessionId;
	}
}
