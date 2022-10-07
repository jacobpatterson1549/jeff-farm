package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class UserTest
{
	private Jsonb jsonb;
	
	@Before
	public void setUp()
	{
		jsonb = JsonbBuilder.create();
	}

	@Test
	public void testSerialize()
	{
		int id = 1996;
		String userName = "daPresident";
		String password = "CRAZY_kat17";
		String firstName = "Bob";
		String lastName = "Dole";
		User user = new User()
				.setId(id)
				.setUserName(userName)
				.setPassword(password)
				.setFirstName(firstName)
				.setLastName(lastName);
		
		String serializedUser = jsonb.toJson(user);

		assertThat(serializedUser.contains("id"), is(true));
		assertThat(serializedUser.contains("userName"), is(true));
		assertThat("password should not be sent back to the ui",
				serializedUser.contains("password"), is(false));
		assertThat(serializedUser.contains("firstName"), is(true));
		assertThat(serializedUser.contains("lastName"), is(true));
	}
	
	@Test
	public void testDeserialize_noPassword()
	{
		String serializedUser = "{\"id\":1996,\"userName\":\"daPresident\",\"firstName\":\"Bob\",\"lastName\":\"Dole\"}";

		User user = jsonb.fromJson(serializedUser, User.class);

		assertThat(user.getId(), is(1996));
		assertThat("no password needs to be provided to update a User", user.getPassword(), is(nullValue()));
		assertThat(user.getUserName(), is("daPresident"));
		assertThat(user.getFirstName(), is("Bob"));
		assertThat(user.getLastName(), is("Dole"));
	}
	
	@Test
	public void testDeserialize_passwordDeserialized()
	{
		String serializedUser = "{\"id\":1996,\"userName\":\"DaPresident\",\"password\":\"CRAZY_kat17\",\"firstName\":\"Bob\",\"lastName\":\"Dole\"}";

		User user = jsonb.fromJson(serializedUser, User.class);

		assertThat("password should be deserialized when a User is created from the ui", user.getPassword(), is("CRAZY_kat17"));
	}
}
