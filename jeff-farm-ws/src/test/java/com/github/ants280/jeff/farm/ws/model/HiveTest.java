package com.github.ants280.jeff.farm.ws.model;

import static com.github.ants280.jeff.farm.ws.JsonProvider.OBJECT_MAPPER;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class HiveTest
{
	@Test
	public void testGetters()
	{
		int id = 1;
		int farmId = 2;
		String name = "name3";
		int queenColor = 0xFF0000; // red
		Hive hive = new Hive(id, farmId, name, queenColor, null, null);
		
		assertEquals(id, hive.getId());
		assertEquals(farmId, hive.getFarmId());
		assertEquals(name, hive.getName());
		assertEquals(16711680, queenColor); // sanity
		assertEquals("#ff0000", hive.getQueenColor()); // [special string conversion for ui]
	}
	
	@Test
	public void testDeserialize_minimal() throws IOException
	{
		String serializedFarm = "{\"id\":2,\"farmId\":3,\"name\":\"name2\",\"queenColor\":\"#ff0000\"}";

		Hive hive = OBJECT_MAPPER.readValue(serializedFarm, Hive.class);

		assertEquals(2, hive.getId());
		assertEquals(3, hive.getFarmId());
		assertEquals("name2", hive.getName());
		assertEquals("#ff0000", hive.getQueenColor());
		assertEquals(null, hive.getCreatedDate());
		assertEquals(null, hive.getModifiedDate());
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_colorWithoutPoundSign() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"00ff00\"}";

		OBJECT_MAPPER.readValue(serializedFarm, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_large() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#1000000\"}";

		OBJECT_MAPPER.readValue(serializedFarm, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_small() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#fff\"}";

		OBJECT_MAPPER.readValue(serializedFarm, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_badHex() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#alfred\"}";

		OBJECT_MAPPER.readValue(serializedFarm, Hive.class);

		fail("expected to fail");
	}
	
	@Test
	public void testDeserialize_UPPERCASE_ok() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#FF0000\"}";

		Hive hive = OBJECT_MAPPER.readValue(serializedFarm, Hive.class);

		assertEquals(0xff0000, hive.getQueenColorInteger());
	}
	
	@Test
	public void testDeserialize_okProblemColor() throws IOException
	{
		String serializedFarm = "{\"queenColor\":\"#90FAfa\"}";

		Hive hive = OBJECT_MAPPER.readValue(serializedFarm, Hive.class);

		assertEquals(0x90FAFA, hive.getQueenColorInteger());
	}
}
