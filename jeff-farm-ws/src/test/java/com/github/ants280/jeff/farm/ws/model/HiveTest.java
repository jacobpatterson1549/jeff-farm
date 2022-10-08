package com.github.ants280.jeff.farm.ws.model;

import java.util.Arrays;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Enclosed.class)
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
	
	@RunWith(Parameterized.class)
	public static class InvalidDeserializeColorTest
	{
		private final String name;
		private final String serializedHive;
		private final Jsonb jsonb;

		public InvalidDeserializeColorTest(String name, String serializedHive)
		{
			this.name = name;
			this.serializedHive = serializedHive;
			this.jsonb = JsonbBuilder.create();
		}

		@Parameterized.Parameters(name = "{0}: serializedHive: {1}")
		public static Iterable<Object[]> data()
		{
			return Arrays.asList(
					// new Object[] { 1, ""},
					new Object[] { "colorWithoutPoundSign" , "{\"queenColor\":\"00ff00\"}" },
					new Object[] { "invalidColor_null" , "{{\"queenColor\":\"1\"}" },
					new Object[] { "invalidColor_large" , "{\"queenColor\":null}" },
					new Object[] { "invalidColor_small" , "{\"queenColor\":\"#fff\"}" },
					new Object[] { "invalidColor_badHex", "{\"queenColor\":\"#alfred\"}" });
		}

		@Test(expected = Exception.class)
		public void testDeserialize()
		{
			jsonb.fromJson(serializedHive, Hive.class);

			fail("expected " + name + " to fail");
		}
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
