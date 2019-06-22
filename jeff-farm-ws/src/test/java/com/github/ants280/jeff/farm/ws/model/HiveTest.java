package com.github.ants280.jeff.farm.ws.model;

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
	public void setUp()
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
				.setParentId(farmId)
				.setName(name)
				.setQueenColorInteger(queenColor);
		
		assertThat(hive.getId(), is(id));
		assertThat(hive.getParentId(), is(farmId));
		assertThat(hive.getName(), is(name));
		assertThat(hive.getQueenColorInteger(), is(queenColor));
		assertThat(hive.getQueenColor(), is("#ff0000")); // [special string conversion for ui]
	}
	
	@Test
	public void testDeserialize_minimal()
	{
		String serializedFarm = "{\"id\":2,\"parentId\":3,\"name\":\"name2\",\"queenColor\":\"#ff0000\"}";

		Hive hive = jsonb.fromJson(serializedFarm, Hive.class);

		assertThat(hive.getId(), is(2));
		assertThat(hive.getParentId(), is(3));
		assertThat("name2", hive.getName(), is("name2"));
		assertThat( hive.getQueenColor(), is("#ff0000"));
		assertThat(hive.getCreatedDate(), is(nullValue()));
		assertThat(hive.getModifiedDate(), is(nullValue()));
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_colorWithoutPoundSign()
	{
		String serializedHive = "{\"queenColor\":\"00ff00\"}";

		jsonb.fromJson(serializedHive, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_null()
	{
		String serializedHive = "{{\"queenColor\":\"1\"}";

		jsonb.fromJson(serializedHive, Hive.class);

		fail("expected to fail");
	}
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_large()
	{
		String serializedHive = "{\"queenColor\":null}";

		jsonb.fromJson(serializedHive, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_small()
	{
		String serializedHive = "{\"queenColor\":\"#fff\"}";

		jsonb.fromJson(serializedHive, Hive.class);

		fail("expected to fail");
	}
	
	@Test(expected = Exception.class)
	public void testDeserialize_invalidColor_badHex()
	{
		String serializedHive = "{\"queenColor\":\"#alfred\"}";

		jsonb.fromJson(serializedHive, Hive.class);

		fail("expected to fail");
	}
	
	@Test
	public void testDeserialize_UPPERCASE_ok()
	{
		String serializedHive = "{\"queenColor\":\"#FF0000\"}";

		Hive hive = jsonb.fromJson(serializedHive, Hive.class);

		assertThat(hive.getQueenColorInteger(), is(0xff0000));
	}
	
	@Test
	public void testDeserialize_okProblemColor()
	{
		String serializedHive = "{\"queenColor\":\"#90FAfa\"}";

		Hive hive = jsonb.fromJson(serializedHive, Hive.class);

		assertThat(hive.getQueenColorInteger(), is(0x90FAFA));
	}
}
