package com.github.ants280.jeff.farm.ws.model;

import static com.github.ants280.jeff.farm.ws.JsonProvider.OBJECT_MAPPER;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserTest
{
	@Test
	public void testSerialize() throws IOException
	{
		int id = 1996;
		String userName = "daPrez";
		String password = "crAAZYkat17";
		String firstName = "Bob";
		String lastName = "Dole";
		User user = new User(id, userName, password, firstName, lastName, null, null);
		
		String serializedUser = OBJECT_MAPPER.writeValueAsString(user);

		assertThat(serializedUser.contains("id"), is(true));
		assertThat(serializedUser.contains("userName"), is(true));
		assertThat("password should not be sent back to the ui",
				serializedUser.contains("password"), is(false));
		assertThat(serializedUser.contains("firstName"), is(true));
		assertThat(serializedUser.contains("lastName"), is(true));
	}
	
	@Test
	public void testDeserialize_noPassword() throws IOException
	{
		String serializedUser = "{\"id\":1996,\"userName\":\"daPrez\",\"firstName\":\"Bob\",\"lastName\":\"Dole\"}";

		User user = OBJECT_MAPPER.readValue(serializedUser, User.class);

		assertThat(user.getId(), is(1996));
		assertThat("no password needs to be provided to update a User", user.getPassword(), is(nullValue()));
		assertThat(user.getUserName(), is("daPrez"));
		assertThat(user.getFirstName(), is("Bob"));
		assertThat(user.getLastName(), is("Dole"));
	}
	
	@Test
	public void testDeserialize_passwordDeserialized() throws IOException
	{
		String serializedUser = "{\"id\":1996,\"userName\":\"daPrez\",\"password\":\"crAAZYkat17\",\"firstName\":\"Bob\",\"lastName\":\"Dole\"}";

		User user = OBJECT_MAPPER.readValue(serializedUser, User.class);

		assertThat("password should be deserialized when a User is created from the ui", user.getPassword(), is("crAAZYkat17"));
	}
}
