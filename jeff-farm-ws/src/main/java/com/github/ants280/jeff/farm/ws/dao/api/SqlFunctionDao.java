package com.github.ants280.jeff.farm.ws.dao.api;

import com.github.ants280.jeff.farm.ws.dao.api.call.SingleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.SimpleResultSetTransformer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;

public class SqlFunctionDao
{
	public static final String SET_USER_ID_FUNCTION_NAME = "set_user_id";
	private final DataSource dataSource;

	public SqlFunctionDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	protected final <T> List<T> execute(
		Integer userId, SqlFunctionCall<T> functionCall)
	{
		return this.execute0(userId, functionCall);
	}

	@SafeVarargs
	protected final <T> T executeSingle(
		Integer userId, SqlFunctionCall<T>... functionCalls)
	{
		List<T> results = this.execute0(userId, functionCalls);
		if (results == null || results.size() != 1)
		{
			throw new SqlDaoException(String.format(
				"Not one result.  Got %d",
				results == null ? null : results.size()));
		}
		return results.get(0);
	}

	@SafeVarargs
	protected final <T> void executeUpdate(
		Integer userId, SqlFunctionCall<T>... functionCalls)
	{
		if (functionCalls == null || functionCalls.length == 0)
		{
			throw new SqlDaoException("No function calls specified");
		}
		this.execute0(userId, functionCalls);
	}

	@SafeVarargs
	private final <T> List<T> execute0(
		Integer userId, SqlFunctionCall<T>... functionCalls)
	{
		if (functionCalls == null || functionCalls.length == 0)
		{
			throw new SqlDaoException("No function calls to execute.");
		}
		try (Connection connection = dataSource.getConnection())
		{
			return execute(connection, userId, functionCalls);
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	private <T> List<T> execute(
		Connection connection,
		Integer userId,
		SqlFunctionCall<T>[] functionCalls) throws SQLException
	{
		try
		{
			if (userId != null)
			{
				connection.setAutoCommit(false);
				this.setUserId(userId, connection);
			}

			return this.execute(connection, functionCalls);
		}
		catch (SQLException ex)
		{
			if (userId != null)
			{
				connection.rollback();
			}
			throw ex;
		}
		finally
		{
			if (userId != null)
			{
				connection.commit();
			}
		}
	}

	private <T> List<T> execute(
		Connection connection, SqlFunctionCall<T>[] sqlFunctionCalls)
		throws SQLException
	{
		if (sqlFunctionCalls.length == 1)
		{
			return this.execute(connection, sqlFunctionCalls[0]);
		}

		// Be lazy, return value from first function call.
		// This is that is needed for inserting CrudItemGroups
		List<T> firstResult = null;
		for (int i = 0; i < sqlFunctionCalls.length; i++)
		{
			List<T> result = this.execute(connection, sqlFunctionCalls[i]);
			if (i == 0)
			{
				firstResult = result;
			}
		}
		return firstResult;
	}

	private <T> List<T> execute(
		Connection connection, SqlFunctionCall<T> sqlFunctionCall)
		throws SQLException
	{
		String sql = sqlFunctionCall.getFunctionCallSql();
		try (CallableStatement callableStatement = connection.prepareCall(sql))
		{
			sqlFunctionCall.setParameters(callableStatement);

			return sqlFunctionCall.execute(callableStatement);
		}
	}

	private void setUserId(int userId, Connection connection)
		throws SQLException
	{
		SqlFunctionParameter<Integer>
			userIdParameter
			= new IntegerSqlFunctionParameter("id", userId);
		SqlFunctionCall<Integer>
			sqlFunctionCall
			= new SingleCommandSqlFunctionCall<>(
			SET_USER_ID_FUNCTION_NAME,
			Collections.singletonList(userIdParameter),
			new SimpleResultSetTransformer<>(
				true,
				resultSet -> resultSet.getInt(SET_USER_ID_FUNCTION_NAME)));
		Integer setUserId = this.execute(connection, sqlFunctionCall).get(0);

		if (setUserId == null || setUserId != userId)
		{
			throw new SqlDaoException(String.format(
				"Setting the user id to %d actually set it ot %s.",
				userId,
				setUserId));
		}
	}
}
