package com.github.ants280.jeff.farm.ws.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;

public class SqlFunctionDao
{
	private static final String SET_USER_ID_FUNCTION_NAME = "set_user_id";
	private final DataSource dataSource;

	public SqlFunctionDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	protected <T> List<T> execute(
		SqlFunctionCall<T> functionCall, final Integer userId)
	{
		try (Connection connection = dataSource.getConnection())
		{
			return execute(connection, functionCall, userId);
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	private <T> List<T> execute(
		Connection connection, SqlFunctionCall<T> functionCall, Integer userId)
		throws SQLException
	{
		try
		{
			if (userId != null)
			{
				connection.setAutoCommit(false);
				this.setUserId(userId, connection);
			}

			return this.execute(connection, functionCall);
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
		SqlFunctionParameter<Integer> userIdParameter
			= new SqlFunctionParameter<>("id", userId, Types.INTEGER);
		SqlFunctionCall<Integer> sqlFunctionCall
			= new SingleCommandSqlFunctionCall<>(
				SET_USER_ID_FUNCTION_NAME,
				Collections.singletonList(userIdParameter),
				new SimpleResultSetTransformer<>(
					true, resultSet -> resultSet.getInt(SET_USER_ID_FUNCTION_NAME)));
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
