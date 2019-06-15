package com.github.ants280.jeff.farm.ws.dao.api;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.dao.UserIdDao;
import com.github.ants280.jeff.farm.ws.dao.api.call.SimpleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
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
	private UserIdDao mockUserIdDao;

	@SuppressWarnings("unchecked")
	// (mocking the function call is incorrectly flagged)
	private static <T> SqlFunctionCall<T> createMockFunctionCall()
	{
		return (SqlFunctionCall<T>) mock(SqlFunctionCall.class);
	}

	@Before
	public void setUp() throws SQLException
	{
		mockConnection = mock(Connection.class);
		DataSource mockDataSource = mock(DataSource.class);
		when(mockDataSource.getConnection()).thenReturn(mockConnection);
		mockUserIdDao = mock(UserIdDao.class);
		when(mockUserIdDao.getUserId()).thenReturn(-1);
		sqlFunctionDao = new SqlFunctionDao(mockDataSource, mockUserIdDao);
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
	public void testCreateRolledBack() throws SQLException
	{
		String exceptionMessage = "call fails for test";
		SqlFunctionCall<Object> mockFunctionCall = createMockFunctionCall();
		doThrow(new SqlDaoException(exceptionMessage)).when(mockFunctionCall)
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

	@Test
	public void testCreateRolledBackNoUserId() throws SQLException
	{
		String exceptionMessage = "no user id";
		SqlFunctionCall<Object>
			mockFunctionCall
			= new SimpleCommandSqlFunctionCall<>(
			"sql",
			Collections.emptyList(),
			resultSet -> null,
			mockUserIdDao);
		doThrow(new RuntimeException(exceptionMessage)).when(mockUserIdDao)
			.getUserId();

		try
		{
			Object output = sqlFunctionDao.execute(mockFunctionCall);
			fail("Expected exception, but got: " + output);
		}
		catch (RuntimeException ex)
		{
			assertThat(ex.getMessage(), containsString(exceptionMessage));
			verify(mockConnection).rollback();
		}
	}
}
