package com.github.ants280.jeff.farm.ws.model;

import static com.github.ants280.jeff.farm.ws.JsonProvider.OBJECT_MAPPER;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class CrudItemTest
{
	@Test
	public void testGetId()
	{
		int id = 8;
		CrudItem crudItem = new CrudItemImpl(id, null, null);

		int id1 = crudItem.getId();

		assertEquals(id, id1);
	}

	@Test
	public void testGetCreatedDate()
	{
		Timestamp createdDate = Timestamp.from(Instant.now());
		CrudItem crudItem = new CrudItemImpl(-1, createdDate, null);

		String createdDate1 = crudItem.getCreatedDate();

		assertNotEquals(null, createdDate1); // NOT-EQUALS
	}

	@Test
	public void testGetModifiedDate()
	{
		Timestamp modifiedDate = Timestamp.from(Instant.now());
		CrudItem crudItem = new CrudItemImpl(-1, null, modifiedDate);

		String modifiedDate1 = crudItem.getModifiedDate();

		assertNotEquals(null, modifiedDate1); // NOT-EQUALS
	}

	@Test
	public void testSerialize() throws IOException
	{
		int id = 1;
		Timestamp createdDate = Timestamp.from(Instant.now());
		Timestamp modifiedDate = createdDate;
		CrudItemImpl crudItem1 = new CrudItemImpl(id, createdDate, modifiedDate);
		
		String serializedCrudItemImpl = OBJECT_MAPPER.writeValueAsString(crudItem1);

		assertEquals(true, serializedCrudItemImpl.contains("id"));
		assertEquals(true, serializedCrudItemImpl.contains("createdDate"));
		assertEquals(true, serializedCrudItemImpl.contains("modifiedDate"));
	}

	@Test
	public void testDeserialize_minimal() throws IOException
	{
		String serializedCrudItemImpl = "{\"id\":2}";

		CrudItemImpl crudItem2 = OBJECT_MAPPER.readValue(serializedCrudItemImpl, CrudItemImpl.class);

		assertEquals(2, crudItem2.getId());
		assertEquals(null, crudItem2.getCreatedDate());
		assertEquals(null, crudItem2.getModifiedDate());
	}

	@Test
	public void testDeserialize_full() throws IOException
	{
		String serializedCrudItemImpl = "{\"id\":3,\"createdDate\":\"Friday, March 1, 2019 2:18:33 PM PST\",\"modifiedDate\":\"Saturday, March 2, 2019 8:55:17 AM PST\"}";

		CrudItemImpl crudItem2 = OBJECT_MAPPER.readValue(serializedCrudItemImpl, CrudItemImpl.class);

		assertEquals(3, crudItem2.getId());
		assertEquals(null, crudItem2.getCreatedDate());
		assertEquals(null, crudItem2.getModifiedDate());
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
