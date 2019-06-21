package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserPasswordReplacementTest
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
		UserPasswordReplacement userPasswordReplacement = new UserPasswordReplacement()
			.setId(61)
			.setCurrentPassword("apple")
			.setNewPassword("B4n4n4");
		String expectedValue = "{\"id\":61,\"currentPassword\":\"apple\",\"newPassword\":\"B4n4n4\"}";

		String actualValue = jsonb.toJson(userPasswordReplacement);

		assertThat("Pojo must have public getters to serialize properly.",
			actualValue, is(expectedValue));
	}
}
