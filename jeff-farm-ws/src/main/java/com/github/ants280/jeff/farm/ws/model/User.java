package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.annotation.JsonbTransient;

public class User extends CrudItem<User>
{
	public static final String USER_NAME_COLUMN = "user_name";
	public static final String PASSWORD_COLUMN = "user_password";
	public static final String FIRST_NAME_COLUMN = "first_name";
	public static final String LAST_NAME_COLUMN = "last_name";
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	
	@Override
	protected User getThis()
	{
		return this;
	}
	
	public String getUserName()
	{
		return userName;
	}

	public User setUserName(String userName)
	{
		this.userName = userName;
		return this;
	}

	@JsonbTransient
	public String getPassword()
	{
		return password;
	}

	public User setPassword(String password)
	{
		this.password = password;
		return this;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public User setFirstName(String firstName)
	{
		this.firstName = firstName;
		return this;
	}

	public String getLastName()
	{
		return lastName;
	}

	public User setLastName(String lastName)
	{
		this.lastName = lastName;
		return this;
	}
}
