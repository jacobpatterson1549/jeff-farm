package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class PoultryInspectionGroupTest
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
		String serializedFarm = "{\"inspectionItems\":[{\"targetId\":5,\"targetName\":\"duck\",\"birdCount\":2,\"eggCount\":1},{\"targetId\":6,\"targetName\":\"chicken\",\"birdCount\":17,\"eggCount\":6}],\"notes\":\"first inspection\",\"farmId\":14}";

		PoultryInspectionGroup pig = jsonb.fromJson(serializedFarm, PoultryInspectionGroup.class);

		assertThat(pig.getFarmId(), is(14));
		assertThat(pig.getNotes(), is("first inspection"));
		assertThat(pig.getInspectionItems().size(), is(2));
		assertThat(pig.getInspectionItems().get(0).getTargetId(), is(5));
		assertThat(pig.getInspectionItems().get(0).getTargetName(), is("duck")); // not used, but worth checking
		assertThat(pig.getInspectionItems().get(0).getBirdCount(), is(2));
		assertThat(pig.getInspectionItems().get(0).getEggCount(), is(1));
		assertThat(pig.getInspectionItems().get(1).getTargetId(), is(6));
		assertThat(pig.getInspectionItems().get(1).getTargetName(), is("chicken")); // not used, but worth checking
		assertThat(pig.getInspectionItems().get(1).getBirdCount(), is(17));
		assertThat(pig.getInspectionItems().get(1).getEggCount(), is(6));
	}
}
