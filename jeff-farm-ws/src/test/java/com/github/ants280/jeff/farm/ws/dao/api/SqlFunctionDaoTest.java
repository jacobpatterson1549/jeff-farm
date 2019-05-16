package com.github.ants280.jeff.farm.ws.dao.api;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.dao.api.call.SingleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.SimpleResultSetTransformer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import javax.sql.DataSource;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
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
		SqlFunctionCall<Integer>
			mockFunctionCall
			= (SqlFunctionCall<Integer>) mock(SqlFunctionCall.class);
		when(mockFunctionCall.execute(any())).thenReturn(Collections.singletonList(
			expectedCreationId));
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

		Integer actualCreationId = sqlFunctionDao.executeSingle(userId,
			mockFunctionCall);

		verify(mockConnection).commit();
		assertThat(actualCreationId, is(equalTo(expectedCreationId)));
	}

	@Test
	public void testCreateReturnMultipleRolledBack() throws SQLException
	{
		int userId = 111;
		SqlFunctionCall<Integer>
			mockFunctionCall
			= new SingleCommandSqlFunctionCall<>("dummy_function_name",
			Collections.emptyList(),
			new SimpleResultSetTransformer<>(true,
				rs -> rs.getInt("column_name_xyz")));
		ResultSet mockCreateIdResultSet = mock(ResultSet.class);
		when(mockCreateIdResultSet.next()).thenReturn(true, true, true, false);
		when(mockCreateIdResultSet.getInt("column_name_xyz")).thenReturn(-1,
			-2,
			-3); // NOT 1 row!!!
		CallableStatement
			mockCreateIdCallableStatement
			= mock(CallableStatement.class);
		when(mockCreateIdCallableStatement.execute()).thenReturn(true);
		when(mockCreateIdCallableStatement.getMoreResults()).thenReturn(false);
		when(mockCreateIdCallableStatement.getResultSet()).thenReturn(
			mockCreateIdResultSet);
		when(mockConnection.prepareCall(any())).thenReturn(
			mockCreateIdCallableStatement);
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
			sqlFunctionDao.executeSingle(userId, mockFunctionCall);
		}
		catch (JeffFarmWsException ex)
		{
			assertThat(ex.getMessage(),
				containsString("Expected single record"));
			verify(mockConnection).rollback();
		}
	}
}
