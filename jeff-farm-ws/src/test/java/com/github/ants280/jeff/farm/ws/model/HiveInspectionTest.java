package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
	public void testDeserialize_nullBooleanShouldBeFalse()
	{
		String serializedHiveInspection = "{\"hiveId\":47,\"queenSeen\":true,\"eggsSeen\":null,\"layingPatternStars\":3,\"temperamentStars\":4,\"queenCells\":1,\"supersedureCells\":2,\"swarmCells\":3,\"combBuildingStars\":2,\"framesSealedBrood\":0,\"framesOpenBrood\":8,\"framesHoney\":6,\"weather\":\"hott\",\"temperatureF\":83,\"windSpeedMph\":6,\"id\":null}";

		HiveInspection hiveInspection = jsonb.fromJson(serializedHiveInspection, HiveInspection.class);

		assertThat(hiveInspection.getQueenSeen(), is(true));
		assertThat("When a hiveInspection is created, the checkboxes are initially false.",
			hiveInspection.getEggsSeen(), is(false));
	}
}
