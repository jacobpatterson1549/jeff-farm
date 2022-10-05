package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class PoultryInspectionGroupUpdateTest
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
		String serializedFarm = "{\"group\":{\"inspectionItems\":[{\"id\":8,\"parentId\":18,\"targetId\":5,\"targetName\":\"duck\",\"birdCount\":10,\"eggCount\":20}],\"parentId\":14,\"id\":18,\"notes\":\"myNotes\"}"
			+ ",\"addItems\":[{\"targetId\":6,\"targetName\":\"chicken\",\"birdCount\":3,\"eggCount\":4}]"
			+ ",\"removeItemIds\":[9]}";

		PoultryInspectionGroupUpdate
			pigUpdate = jsonb.fromJson(serializedFarm, PoultryInspectionGroupUpdate.class);

		assertThat(pigUpdate.getGroup().getParentId(), is(14));
		assertThat(pigUpdate.getGroup().getNotes(), is("myNotes"));
		assertThat(pigUpdate.getGroup().getInspectionItems().size(), is(1));
		assertThat(pigUpdate.getGroup().getInspectionItems().get(0).getId(), is(8));
		assertThat(pigUpdate.getGroup().getInspectionItems().get(0).getParentId(), is(18));
		assertThat(pigUpdate.getGroup().getInspectionItems().get(0).getTargetId(), is(5));
		assertThat(pigUpdate.getGroup().getInspectionItems().get(0).getTargetName(), is("duck")); // not used, but worth checking
		assertThat(pigUpdate.getGroup().getInspectionItems().get(0).getBirdCount(), is(10));
		assertThat(pigUpdate.getGroup().getInspectionItems().get(0).getEggCount(), is(20));
		assertThat(pigUpdate.getAddItems().length, is(1));
		assertThat(pigUpdate.getAddItems()[0].getTargetId(), is(6));
		assertThat(pigUpdate.getAddItems()[0].getTargetName(), is("chicken")); // not used, but worth checking
		assertThat(pigUpdate.getAddItems()[0].getBirdCount(), is(3));
		assertThat(pigUpdate.getAddItems()[0].getEggCount(), is(4));
		assertThat(pigUpdate.getRemoveItemIds().length, is(1));
		assertThat(pigUpdate.getRemoveItemIds()[0], is(9));
	}
}
