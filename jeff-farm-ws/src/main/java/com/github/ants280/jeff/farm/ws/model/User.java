package com.github.ants280.jeff.farm.ws.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;

public class User extends CrudItem
{
	public static final String USER_NAME_COLUMN = "user_name";
	public static final String PASSWORD_COLUMN = "user_password";
	public static final String FIRST_NAME_COLUMN = "first_name";
	public static final String LAST_NAME_COLUMN = "last_name";
	private final String userName;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private final String password;
	private final String firstName;
	private final String lastName;

	public User(
			int id,
			String userName,
			String password,
			String firstName,
			String lastName,
			Timestamp createdDate,
			Timestamp modifiedDate)
	{
		super(id, createdDate, modifiedDate);
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}
	
	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}
}
