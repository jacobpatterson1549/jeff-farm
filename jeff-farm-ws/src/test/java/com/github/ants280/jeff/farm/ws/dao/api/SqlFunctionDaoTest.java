package com.github.ants280.jeff.farm.ws.dao.api;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SqlFunctionDaoTest
{
	private SqlFunctionDao sqlFunctionDao;
	private DataSource mockDataSource;
	private Connection mockConnection;

	@Before
	public void setUp() throws SQLException
	{
		mockConnection = mock(Connection.class);
		mockDataSource = mock(DataSource.class);
		when(mockDataSource.getConnection()).thenReturn(mockConnection);
		sqlFunctionDao = new SqlFunctionDao(mockDataSource) {};
	}

	@Test
	public void testCreateBasic() throws SQLException
	{
		int userId = 111;
		int expectedCreationId = 1;
		SqlFunctionCall<Integer> mockFunctionCall = createMockFunctionCall();
		when(mockFunctionCall.getResult()).thenReturn(expectedCreationId);
		// set userId mocking :
		// TODO: Move userId setting to the database.  Have database throw exception if user not valid for the applicable CRUD operation
		ResultSet mockUserIdResultSet = mock(ResultSet.class);
		when(mockUserIdResultSet.next()).thenReturn(true, false);
		when(mockUserIdResultSet.getInt(SqlFunctionDao.SET_USER_ID_FUNCTION_NAME))
			.thenReturn(userId);
		CallableStatement mockSetUserIdCallableStatement = mock(
			CallableStatement.class);
		when(mockSetUserIdCallableStatement.execute()).thenReturn(true);
		when(mockSetUserIdCallableStatement.getMoreResults()).thenReturn(false);
		when(mockSetUserIdCallableStatement.getResultSet()).thenReturn(
			mockUserIdResultSet);
		when(mockConnection.prepareCall(contains(SqlFunctionDao.SET_USER_ID_FUNCTION_NAME)))
			.thenReturn(mockSetUserIdCallableStatement);

		Integer actualCreationId = sqlFunctionDao.execute(userId,
			mockFunctionCall);

		verify(mockConnection).commit();
		assertThat(actualCreationId, is(equalTo(expectedCreationId)));
	}

	@Test
	public void testCreateReturnMultipleRolledBack() throws SQLException
	{
		int userId = 111;
		String exceptionMessage = "call fails for test";
		SqlFunctionCall<Object> mockFunctionCall = createMockFunctionCall();
		doThrow(new SqlDaoException(exceptionMessage))
			.when(mockFunctionCall)
			.execute(any());
		// set userId mocking :
		ResultSet mockUserIdResultSet = mock(ResultSet.class);
		when(mockUserIdResultSet.next()).thenReturn(true, false);
		when(mockUserIdResultSet.getInt(SqlFunctionDao.SET_USER_ID_FUNCTION_NAME))
			.thenReturn(userId);
		CallableStatement mockSetUserIdCallableStatement = mock(
			CallableStatement.class);
		when(mockSetUserIdCallableStatement.execute()).thenReturn(true);
		when(mockSetUserIdCallableStatement.getMoreResults()).thenReturn(false);
		when(mockSetUserIdCallableStatement.getResultSet()).thenReturn(
			mockUserIdResultSet);
		when(mockConnection.prepareCall(contains(SqlFunctionDao.SET_USER_ID_FUNCTION_NAME)))
			.thenReturn(mockSetUserIdCallableStatement);

		try
		{
			Object output = sqlFunctionDao.execute(userId, mockFunctionCall);
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
