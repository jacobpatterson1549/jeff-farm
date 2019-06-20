package com.github.ants280.jeff.farm.ws.model;

public class UserPasswordReplacement
{
	public static final String USER_ID_COLUMN = "user_id";
	public static final String OLD_PASSWORD_COLUMN = "old_user_password";
	public static final String NEW_PASSWORD_COLUMN = "new_user_password";
	public static final String PASSWORD_UPDATED_OUT_VALUE = "user_password_updated";
	private final int userId;
	private final String oldPassword;
	private final String newPassword;

	public UserPasswordReplacement(
		int userId, String oldPassword, String newPassword)
	{
		this.userId = userId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public int getUserId()
	{
		return userId;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}
}
