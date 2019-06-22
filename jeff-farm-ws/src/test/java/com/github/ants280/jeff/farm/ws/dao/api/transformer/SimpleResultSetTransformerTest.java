package com.github.ants280.jeff.farm.ws.dao.api.transformer;

import com.github.ants280.jeff.farm.ws.dao.api.SqlDaoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SimpleResultSetTransformerTest
{
	@Test
	public void testTransform_noRows() throws SQLException
	{
		ResultSet mockResultSet = mock(ResultSet.class);
		when(mockResultSet.next()).thenReturn(false);
		ResultSetTransformer r = new SimpleResultSetTransformer<>(null);

		Object result = r.transform(mockResultSet);

		assertThat(result, is(nullValue()));
		verify(mockResultSet, times(1)).next();
	}

	@Test
	public void testTransform_oneRow() throws SQLException
	{
		ResultSet mockResultSet = mock(ResultSet.class);
		when(mockResultSet.next()).thenReturn(true, false);
		when(mockResultSet.getString("a")).thenReturn("b");
		ResultSetTransformer<String> resultSetTransformer
			= new SimpleResultSetTransformer<>(rs -> rs.getString("a"));

		String result = resultSetTransformer.transform(mockResultSet);

		assertThat(result, is("b"));
		verify(mockResultSet, times(2)).next();
	}


	@Test(expected = SqlDaoException.class)
	public void testTransform_twoRows() throws SQLException
	{
		ResultSet mockResultSet = mock(ResultSet.class);
		when(mockResultSet.next()).thenReturn(true, true);
		ResultSetTransformer<String> resultSetTransformer
			= new SimpleResultSetTransformer<>(rs -> "c");

		resultSetTransformer.transform(mockResultSet);
	}
}
