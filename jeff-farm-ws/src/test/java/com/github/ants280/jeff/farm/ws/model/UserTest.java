package com.github.ants280.jeff.farm.ws.model;

import static com.github.ants280.jeff.farm.ws.JsonProvider.OBJECT_MAPPER;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
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
		System.out.println(serializedUser);

		assertEquals(true, serializedUser.contains("id"));
		assertEquals(true, serializedUser.contains("userName"));
		assertEquals("password should not be sent back to the ui", false, serializedUser.contains("password"));
		assertEquals(true, serializedUser.contains("firstName"));
		assertEquals(true, serializedUser.contains("lastName"));
	}
	
	@Test
	public void testDeserialize_noPassword() throws IOException
	{
		String serializedUser = "{\"id\":1996,\"userName\":\"daPrez\",\"firstName\":\"Bob\",\"lastName\":\"Dole\"}";

		User user = OBJECT_MAPPER.readValue(serializedUser, User.class);

		assertEquals(1996, user.getId());
		assertEquals("no password needs to be provided to update a User", null, user.getPassword());
		assertEquals("daPrez", user.getUserName());
		assertEquals("Bob", user.getFirstName());
		assertEquals("Dole", user.getLastName());
	}
	
	@Test
	public void testDeserialize_passwordDeserialized() throws IOException
	{
		String serializedUser = "{\"id\":1996,\"userName\":\"daPrez\",\"password\":\"crAAZYkat17\",\"firstName\":\"Bob\",\"lastName\":\"Dole\"}";

		User user = OBJECT_MAPPER.readValue(serializedUser, User.class);

		assertEquals("password should be deserialized when a User is created from the ui", "crAAZYkat17", user.getPassword());
	}
}
