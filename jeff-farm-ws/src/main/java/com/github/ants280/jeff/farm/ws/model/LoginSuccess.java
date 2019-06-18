package com.github.ants280.jeff.farm.ws.model;

public class LoginSuccess
{
	private final boolean adminUser;
	private final int userId;

	public LoginSuccess(boolean adminUser, int userId)
	{
		this.adminUser = adminUser;
		this.userId = userId;
	}

	public boolean getAdminUser()
	{
		return adminUser;
	}

	public int getUserId()
	{
		return userId;
	}
}
