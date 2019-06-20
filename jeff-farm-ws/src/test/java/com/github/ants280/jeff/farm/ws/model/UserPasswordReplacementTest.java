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
		UserPasswordReplacement userPasswordReplacement = new UserPasswordReplacement(61, "apple", "B4n4n4");
		String expectedValue = "{\"newPassword\":\"B4n4n4\",\"oldPassword\":\"apple\",\"userId\":61}";

		String actualValue = jsonb.toJson(userPasswordReplacement);

		assertThat("Pojo must have public getters to serialize properly.",
			actualValue, is(expectedValue));
	}
}
