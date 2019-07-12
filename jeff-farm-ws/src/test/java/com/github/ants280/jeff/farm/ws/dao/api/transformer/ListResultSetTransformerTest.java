package com.github.ants280.jeff.farm.ws.dao.api.transformer;

import com.github.ants280.jeff.farm.ws.dao.api.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListResultSetTransformerTest
{
	@Test
	public void testTransform() throws SQLException
	{
		ResultSet mockResultSet = mock(ResultSet.class);
		when(mockResultSet.next()).thenReturn(true, true, true, false);
		AtomicInteger i = new AtomicInteger(0);
		RowMapper<Integer> rowMapper = (resultSet) -> i.incrementAndGet();

		ResultSetTransformer<List<Integer>> listResultSetTransformer
			= new ListResultSetTransformer(rowMapper);
		List<Integer> actualOutput
			= listResultSetTransformer.transform(mockResultSet);
		List<Integer> expectedOutput = Arrays.asList(1, 2, 3);

		assertThat(actualOutput, is(expectedOutput));
		verify(mockResultSet, times(4)).next();
	}
}
