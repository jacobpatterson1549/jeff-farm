package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
public class FarmPermissionTest
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
		FarmPermission farmPermission = new FarmPermission()
			.setId(243)
			.setParentId(14)
			.setUserName("Jacob");

		String json = jsonb.toJson(farmPermission);

		assertThat("Nonexistent createdDate, modifiedDate should not be serialized.",
			json, is("{\"id\":243,\"parentId\":14,\"userName\":\"Jacob\"}"));
	}
}
