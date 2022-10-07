package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class HiveInspectionTest
{
	private Jsonb jsonb;

	@Before
	public void setUp()
	{
		jsonb = JsonbBuilder.create();
	}

	@Test
	public void testDeserialize_basic()
	{
		String serializedHiveInspection = "{\"id\":47,\"parentId\":19,\"queenSeen\":false,\"eggsSeen\":true,\"layingPatternStars\":3,\"temperamentStars\":4,\"queenCells\":11,\"supersedureCells\":22,\"swarmCells\":32,\"combBuildingStars\":5,\"framesSealedBrood\":14,\"framesOpenBrood\":8,\"framesHoney\":6,\"weather\":\"hot\",\"temperatureF\":83,\"windSpeedMph\":17}";

		HiveInspection hiveInspection = jsonb.fromJson(serializedHiveInspection, HiveInspection.class);

		assertThat(hiveInspection.getId(), is(47));
		assertThat(hiveInspection.getParentId(), is(19));
		assertThat(hiveInspection.getQueenSeen(), is(false));
		assertThat(hiveInspection.getEggsSeen(), is(true));
		assertThat(hiveInspection.getLayingPatternStars(), is(3));
		assertThat(hiveInspection.getTemperamentStars(), is(4));
		assertThat(hiveInspection.getQueenCells(), is(11));
		assertThat(hiveInspection.getSupersedureCells(), is(22));
		assertThat(hiveInspection.getSwarmCells(), is(32));
		assertThat(hiveInspection.getCombBuildingStars(), is(5));
		assertThat(hiveInspection.getFramesSealedBrood(), is(14));
		assertThat(hiveInspection.getFramesOpenBrood(), is(8));
		assertThat(hiveInspection.getFramesHoney(), is(6));
		assertThat(hiveInspection.getWeather(), is("hot"));
		assertThat(hiveInspection.getTemperatureF(), is(83));
		assertThat(hiveInspection.getWindSpeedMph(), is(17));
	}

	@Test
	public void testDeserialize_nullBooleanShouldBeFalse()
	{
		String serializedHiveInspection = "{\"queenSeen\":true,\"eggsSeen\":null}";

		HiveInspection hiveInspection = jsonb.fromJson(serializedHiveInspection, HiveInspection.class);

		assertThat(hiveInspection.getQueenSeen(), is(true));
		assertThat("When a hiveInspection is created, the checkboxes are initially false.",
			hiveInspection.getEggsSeen(), is(false));
	}
}
