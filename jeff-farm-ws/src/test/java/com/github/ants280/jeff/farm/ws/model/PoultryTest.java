package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class PoultryTest
{
	private Jsonb jsonb;

	@Before
	public void setUp()
	{
		jsonb = JsonbBuilder.create();
	}

	@Test
	public void testDeserialize_idNotSet()
	{
		String serializedPoultry = "{\"farmId\":17,\"name\":\"robbin\",\"id\":null}";

		Poultry poultry = jsonb.fromJson(serializedPoultry, Poultry.class);

		assertThat(poultry.getFarmId(), is(17));
		assertThat(poultry.getName(), is("robbin"));
		assertThat("When a crudItem is created, it is sent with a null id.",
			poultry.getId(), is(nullValue()));
	}
}
