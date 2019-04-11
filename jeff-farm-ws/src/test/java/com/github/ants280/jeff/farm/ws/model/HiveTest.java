package com.github.ants280.jeff.farm.ws.model;

import java.io.IOException;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class HiveTest
{
	private Jsonb jsonb;
	
	@Before
	public void setUp() throws Exception
	{
		jsonb = JsonbBuilder.create();
	}
	
	@Test
	public void testGetters()
	{
		int id = 1;
		int farmId = 2;
		String name = "name3";
		int queenColor = 0xFF0000; // red
		Hive hive = new Hive()
				.setId(id)
				.setFarmId(farmId)
				.setName(name)
				.setQueenColorInteger(queenColor);
		
		assertThat(hive.getId(), is(id));
		assertThat(hive.getFarmId(), is(farmId));
		assertThat(hive.getName(), is(name));
		assertThat(hive.getQueenColorInteger(), is(queenColor));
		assertThat(hive.getQueenColor(), is("#ff0000")); // [special string conversion for ui]
	}
	
	@Test
	public void testDeserialize_minimal() throws IOException
	{
		String serializedFarm = "{\"id\":2,\"farmId\":3,\"name\":\"name2\",\"queenColor\":\"#ff0000\"}";

		Hive hive = jsonb.fromJson(serializedFarm, Hive.class);

		assertThat(hive.getId(), is(2));
		assertThat(hive.getFarmId(), is(3));
		assertThat("name2", hive.getName(), is("name2"));
		assertThat( hive.getQueenColor(), is("#ff0000"));
		assertThat(hive.getCreatedDate(), is(nullValue()));
		assertThat(hive.getModifiedDate(), is(nullValue()));
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_colorWithoutPoundSign() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"00ff00\"}";

		jsonb.fromJson(serializedFarm, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_large() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#1000000\"}";

		jsonb.fromJson(serializedFarm, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_small() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#fff\"}";

		jsonb.fromJson(serializedFarm, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_badHex() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#alfred\"}";

		jsonb.fromJson(serializedFarm, Hive.class);

		fail("expected to fail");
	}
	
	@Test
	public void testDeserialize_UPPERCASE_ok() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#FF0000\"}";

		Hive hive = jsonb.fromJson(serializedFarm, Hive.class);

		assertThat(hive.getQueenColorInteger(), is(0xff0000));
	}
	
	@Test
	public void testDeserialize_okProblemColor() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#90FAfa\"}";

		Hive hive = jsonb.fromJson(serializedFarm, Hive.class);

		assertThat(hive.getQueenColorInteger(), is(0x90FAFA));
	}
}
