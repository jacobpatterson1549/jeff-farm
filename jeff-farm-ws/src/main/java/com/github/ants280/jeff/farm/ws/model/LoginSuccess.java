package com.github.ants280.jeff.farm.ws.model;

public class LoginSuccess
{
	private final int userId;
	private final boolean isAdminUser;

	public LoginSuccess(int userId, boolean isAdminUser)
	{
		this.userId = userId;
		this.isAdminUser = isAdminUser;
	}

	public int getUserId()
	{
		return userId;
	}

	public boolean isAdminUser()
	{
		return isAdminUser;
	}
}
