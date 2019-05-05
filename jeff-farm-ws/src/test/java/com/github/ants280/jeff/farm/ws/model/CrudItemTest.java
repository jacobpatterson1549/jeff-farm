package com.github.ants280.jeff.farm.ws.model;

import java.sql.Timestamp;
import java.time.Instant;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CrudItemTest
{
	private Jsonb jsonb;
	
	@Before
	public void setUp()
	{
		jsonb = JsonbBuilder.create();
	}
	
	@Test
	public void testGetId()
	{
		int id = 8;
		CrudItem crudItem = new CrudItemImpl().setId(id);

		int id1 = crudItem.getId();

		assertThat(id1, is(id));
	}

	@Test
	public void testGetCreatedDate()
	{
		Timestamp createdDate = Timestamp.from(Instant.now());
		CrudItem crudItem = new CrudItemImpl().setCreatedTimestamp(createdDate);

		String createdDate1 = crudItem.getCreatedDate();

		assertThat(createdDate1, is(not(nullValue())));
	}

	@Test
	public void testGetModifiedDate()
	{
		Timestamp modifiedDate = Timestamp.from(Instant.now());
		CrudItem crudItem = new CrudItemImpl().setModifiedTimestamp(modifiedDate);

		String modifiedDate1 = crudItem.getModifiedDate();

		assertThat(modifiedDate1, is(not(nullValue())));
	}

	@Test
	public void testSerialize()
	{
		int id = 1;
		Timestamp createdDate = Timestamp.from(Instant.now());
		Timestamp modifiedDate = Timestamp.from(Instant.now());
		CrudItemImpl crudItem1 = new CrudItemImpl()
				.setId(id)
				.setCreatedTimestamp(createdDate)
				.setModifiedTimestamp(modifiedDate);
		
		String serializedCrudItemImpl = jsonb.toJson(crudItem1);

		assertThat(serializedCrudItemImpl.contains("id"), is(true));
		assertThat(serializedCrudItemImpl.contains("createdDate"),is(true));
		assertThat(serializedCrudItemImpl.contains("modifiedDate"), is(true));
	}

	@Test
	public void testDeserialize_minimal()
	{
		String serializedCrudItemImpl = "{\"id\":2}";

		CrudItemImpl crudItem2 = jsonb.fromJson(serializedCrudItemImpl, CrudItemImpl.class);

		assertThat(crudItem2.getId(), is(2));
		assertThat(crudItem2.getCreatedDate(), is(nullValue()));
		assertThat(crudItem2.getModifiedDate(), is(nullValue()));
	}

	@Test
	public void testDeserialize_full()
	{
		String serializedCrudItemImpl = "{\"id\":3,\"createdDate\":\"Friday, March 1, 2019 2:18:33 PM PST\",\"modifiedDate\":\"Saturday, March 2, 2019 8:55:17 AM PST\"}";

		CrudItemImpl crudItem2 = jsonb.fromJson(serializedCrudItemImpl, CrudItemImpl.class);

		assertThat(crudItem2.getId(), is(3));
		assertThat(crudItem2.getCreatedDate(), is(nullValue()));
		assertThat(crudItem2.getModifiedDate(), is(nullValue()));
	}
	
	public static class CrudItemImpl extends CrudItem<CrudItemImpl>
	{
		@Override
		protected CrudItemImpl getThis()
		{
			return this;
		}
	}
}
