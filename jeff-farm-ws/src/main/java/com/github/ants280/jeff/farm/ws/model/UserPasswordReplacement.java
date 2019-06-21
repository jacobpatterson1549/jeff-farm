package com.github.ants280.jeff.farm.ws.model;

public class UserPasswordReplacement extends CrudItem<UserPasswordReplacement>
{
	private String currentPassword;
	private String newPassword;

	@Override
	protected UserPasswordReplacement getThis()
	{
		return this;
	}

	public String getCurrentPassword()
	{
		return currentPassword;
	}

	public UserPasswordReplacement setCurrentPassword(String currentPassword)
	{
		this.currentPassword = currentPassword;
		return this;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public UserPasswordReplacement setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
		return this;
	}
}
