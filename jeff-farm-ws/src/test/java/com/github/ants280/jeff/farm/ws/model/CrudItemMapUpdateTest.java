package com.github.ants280.jeff.farm.ws.model;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CrudItemMapUpdateTest
{
	private Jsonb jsonb;

	@Before
	public void setUp()
	{
		jsonb = JsonbBuilder.create();
	}

	@Test
	public void testSerialize_basic()
	{
		CrudItemMapUpdate update = new CrudItemMapUpdate();
		update.setMap(new CrudItemMap()
			.setId(14)
			.setParentId(14)
			.setTargetId(2)
			.setTargetName("Cattle"));

		String serialized = jsonb.toJson(update);

		assertThat(serialized, is("{\"map\":{\"id\":14,\"parentId\":14,\"targetId\":2,\"targetName\":\"Cattle\"}}"));
	}

	@Test
	public void testDeserialize_basic()
	{
		String serialized =
			"{\"map\":{\"parentId\":14,\"coordinates\":[{\"latitude\":87.6,\"longitude\":-242.7,\"displayOrder\":2,\"id\":1},{\"latitude\":67.9,\"longitude\":50.0,\"displayOrder\":1,\"id\":2}],\"id\":1,\"targetId\":2,\"targetName\":\"Cattle\"},\"addCoordinates\":[],\"removeCoordinateIds\":[]}";

		CrudItemMapUpdate update = jsonb.fromJson(serialized,
			CrudItemMapUpdate.class);

		assertThat(update.getMap().getParentId(), is(14));
		assertThat(update.getMap().getCoordinates().size(), is(2));
		assertThat(update.getMap().getCoordinates().get(0).getLatitude(), is(87.6));
		assertThat(update.getMap().getCoordinates().get(0).getLongitude(), is(-242.7));
		assertThat(update.getMap().getCoordinates().get(0).getDisplayOrder(), is(2));
		assertThat(update.getMap().getCoordinates().get(0).getId(), is(1));
		assertThat(update.getMap().getCoordinates().get(1).getLatitude(), is(67.9));
		assertThat(update.getMap().getCoordinates().get(1).getLongitude(), is(50.0));
		assertThat(update.getMap().getCoordinates().get(1).getDisplayOrder(), is(1));
		assertThat(update.getMap().getCoordinates().get(1).getId(), is(2));
		assertThat(update.getMap().getTargetId(), is(2));
		assertThat(update.getMap().getTargetName(), is("Cattle"));
		assertThat(update.getAddCoordinates().length, is(0));
		assertThat(update.getRemoveCoordinateIds().length, is(0));
	}


}
