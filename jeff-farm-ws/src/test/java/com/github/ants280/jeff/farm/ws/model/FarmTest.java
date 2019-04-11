package com.github.ants280.jeff.farm.ws.model;

import static com.github.ants280.jeff.farm.ws.JsonProvider.OBJECT_MAPPER;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class FarmTest
{
	@Test
	public void testGetName()
	{
		String name = "boring farm";
		Farm farm = new Farm(-1, name, null, null, null);

		String name1 = farm.getName();

		assertThat(name1, is(name));
	}

	@Test
	public void testGetLocation()
	{
		String location = "somewhere";
		Farm farm = new Farm(-1, null, location, null, null);

		String location1 = farm.getLocation();

		assertThat(location1, is(location));
	}

	@Test
	public void testGetCreatedDate()
	{
		Timestamp createdDate = Timestamp.from(Instant.now());
		Farm farm = new Farm(-1, null, null, createdDate, null);

		String createdDate1 = farm.getCreatedDate();

		assertThat(createdDate1, is(not(nullValue())));
	}

	@Test
	public void testGetModifiedDate()
	{
		Timestamp modifiedDate = Timestamp.from(Instant.now());
		Farm farm = new Farm(-1, null, null, null, modifiedDate);

		String modifiedDate1 = farm.getModifiedDate();

		assertThat(modifiedDate1, is(not(nullValue()))); // NOT-EQUALS
	}

	@Test
	public void testSerialize() throws IOException
	{
		int id = 1;
		String name = "name1";
		String location = "location1";
		Timestamp createdDate = Timestamp.from(Instant.now());
		Timestamp modifiedDate = createdDate;
		Farm farm1 = new Farm(id, name, location, createdDate, modifiedDate);
		
		String serializedFarm = OBJECT_MAPPER.writeValueAsString(farm1);

		assertThat(serializedFarm.contains("id"), is(true));
		assertThat(serializedFarm.contains("name"), is(true));
		assertThat(serializedFarm.contains("location"), is(true));
		assertThat(serializedFarm.contains("createdDate"), is(true));
		assertThat(serializedFarm.contains("modifiedDate"), is(true));
	}
}
