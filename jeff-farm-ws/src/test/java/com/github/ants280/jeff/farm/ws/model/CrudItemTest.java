package com.github.ants280.jeff.farm.ws.model;

import static com.github.ants280.jeff.farm.ws.JsonProvider.OBJECT_MAPPER;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class CrudItemTest
{
	@Test
	public void testGetId()
	{
		int id = 8;
		CrudItem crudItem = new CrudItemImpl(id, null, null);

		int id1 = crudItem.getId();

		assertThat(id1, is(id));
	}

	@Test
	public void testGetCreatedDate()
	{
		Timestamp createdDate = Timestamp.from(Instant.now());
		CrudItem crudItem = new CrudItemImpl(-1, createdDate, null);

		String createdDate1 = crudItem.getCreatedDate();

		assertThat(createdDate1, is(not(nullValue())));
	}

	@Test
	public void testGetModifiedDate()
	{
		Timestamp modifiedDate = Timestamp.from(Instant.now());
		CrudItem crudItem = new CrudItemImpl(-1, null, modifiedDate);

		String modifiedDate1 = crudItem.getModifiedDate();

		assertThat(modifiedDate1, is(not(nullValue())));
	}

	@Test
	public void testSerialize() throws IOException
	{
		int id = 1;
		Timestamp createdDate = Timestamp.from(Instant.now());
		Timestamp modifiedDate = createdDate;
		CrudItemImpl crudItem1 = new CrudItemImpl(id, createdDate, modifiedDate);
		
		String serializedCrudItemImpl = OBJECT_MAPPER.writeValueAsString(crudItem1);

		assertThat(serializedCrudItemImpl.contains("id"), is(true));
		assertThat(serializedCrudItemImpl.contains("createdDate"),is(true));
		assertThat(serializedCrudItemImpl.contains("modifiedDate"), is(true));
	}

	@Test
	public void testDeserialize_minimal() throws IOException
	{
		String serializedCrudItemImpl = "{\"id\":2}";

		CrudItemImpl crudItem2 = OBJECT_MAPPER.readValue(serializedCrudItemImpl, CrudItemImpl.class);

		assertThat(crudItem2.getId(), is(2));
		assertThat(crudItem2.getCreatedDate(), is(nullValue()));
		assertThat(crudItem2.getModifiedDate(), is(nullValue()));
	}

	@Test
	public void testDeserialize_full() throws IOException
	{
		String serializedCrudItemImpl = "{\"id\":3,\"createdDate\":\"Friday, March 1, 2019 2:18:33 PM PST\",\"modifiedDate\":\"Saturday, March 2, 2019 8:55:17 AM PST\"}";

		CrudItemImpl crudItem2 = OBJECT_MAPPER.readValue(serializedCrudItemImpl, CrudItemImpl.class);

		assertThat(crudItem2.getId(), is(3));
		assertThat(crudItem2.getCreatedDate(), is(nullValue()));
		assertThat(crudItem2.getModifiedDate(), is(nullValue()));
	}
	
	private static class CrudItemImpl extends CrudItem
	{
		public CrudItemImpl(
				int id,
				Timestamp createdDate,
				Timestamp modifiedDate)
		{
			super(id, createdDate, modifiedDate);
		}
	}
}
