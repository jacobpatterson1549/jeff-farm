package com.github.ants280.jeff.farm.ws.dao.api;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SqlFunctionDaoTest
{
	private SqlFunctionDao sqlFunctionDao;
	private Connection mockConnection;

	@Before
	public void setUp() throws SQLException
	{
		mockConnection = mock(Connection.class);
		DataSource mockDataSource = mock(DataSource.class);
		when(mockDataSource.getConnection()).thenReturn(mockConnection);
		sqlFunctionDao = new SqlFunctionDao(mockDataSource);
	}

	@Test
	public void testCreateBasic() throws SQLException
	{
		int expectedCreationId = 1;
		SqlFunctionCall<Integer> mockFunctionCall = createMockFunctionCall();
		when(mockFunctionCall.getResult()).thenReturn(expectedCreationId);

		Integer actualCreationId = sqlFunctionDao.execute(mockFunctionCall);

		verify(mockConnection).commit();
		assertThat(actualCreationId, is(equalTo(expectedCreationId)));
	}

	@Test
	public void testCreateReturnMultipleRolledBack() throws SQLException
	{
		String exceptionMessage = "call fails for test";
		SqlFunctionCall<Object> mockFunctionCall = createMockFunctionCall();
		doThrow(new SqlDaoException(exceptionMessage))
			.when(mockFunctionCall)
			.execute(any(PreparedStatement.class));

		try
		{
			Object output = sqlFunctionDao.execute(mockFunctionCall);
			fail("Expected exception, but got: " + output);
		}
		catch (JeffFarmWsException ex)
		{
			assertThat(ex.getMessage(), containsString(exceptionMessage));
			verify(mockConnection).rollback();
		}
	}

	@SuppressWarnings("unchecked")
	// (mocking the function call is incorrectly flagged)
	private static <T> SqlFunctionCall<T> createMockFunctionCall()
	{
		return (SqlFunctionCall<T>) mock(SqlFunctionCall.class);
	}
}
