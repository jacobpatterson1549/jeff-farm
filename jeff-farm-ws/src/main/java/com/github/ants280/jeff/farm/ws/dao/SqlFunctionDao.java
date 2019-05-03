package com.github.ants280.jeff.farm.ws.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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

	private static String createFunctionCall(
		String functionName, List<SqlFunctionParameter> inParameters)
	{
		return String.format("SELECT * FROM %s(%s)",
			functionName,
			String.join(", ", Collections.nCopies(inParameters.size(), "?")));
	}

	private static void setParameters(
		PreparedStatement preparedStatement,
		List<SqlFunctionParameter> inParameters) throws SQLException
	{
		int index = 1;
		for (SqlFunctionParameter parameter : inParameters)
		{
			setParameter(preparedStatement, parameter, index++);
		}
	}

	private static void setParameter(
		PreparedStatement preparedStatement,
		SqlFunctionParameter parameter,
		int index) throws SQLException
	{
		switch (parameter.getSqlType())
		{
			case Types.VARCHAR:
			case Types.CHAR:
				preparedStatement.setString(index,
					(String) parameter.getValue());
				break;
			case Types.INTEGER:
				preparedStatement.setInt(index, (int) parameter.getValue());
				break;
			case Types.BOOLEAN:
				preparedStatement.setBoolean(index,
					(boolean) parameter.getValue());
				break;
			default:
				throw new IllegalArgumentException(
					"Cannot set parameter of type " + parameter.getSqlType());
		}
	}

	public void executeUpdate(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		int userId)
	{
		this.executeSingle(functionName,
			inParameters,
			resultSet -> null,
			userId);
	}

	public int executeCreate(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		String outParameterName,
		int userId)
	{
		return this.executeSingle(functionName,
			inParameters,
			resultSet -> resultSet.getInt(outParameterName),
			userId);
	}

	public boolean executeReadBoolean(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		String outParameterName)
	{
		return this.executeSingle(functionName,
			inParameters,
			resultSet -> resultSet.getBoolean(outParameterName),
			null);
	}

	public <T> T executeRead(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		RowMapper<T> rowMapper)
	{
		return this.executeSingle(functionName, inParameters, rowMapper, null);
	}

	public <T> List<T> executeReadList(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		RowMapper<T> rowMapper)
	{
		return this.execute(functionName, inParameters, rowMapper, null);
	}

	private <T> T executeSingle(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		RowMapper<T> rowMapper,
		Integer userId)
	{
		List<T> results = this.execute(functionName,
			inParameters,
			rowMapper,
			userId);

		if (results.size() != 1)
		{
			throw new SqlDaoException(String.format("Expected 1 result.  Got %d.",
				results.size()));
		}

		return results.get(0);
	}

	private <T> List<T> execute(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		RowMapper<T> rowMapper,
		final Integer userId)
	{
		try (Connection connection = dataSource.getConnection())
		{
			try
			{
				if (userId != null)
				{
					connection.setAutoCommit(false);
					this.setUserId(userId, connection);
				}

				return this.execute(connection,
					functionName,
					inParameters,
					rowMapper);
			}
			catch (SQLException ex2)
			{
				if (userId != null)
				{
					connection.rollback();
				}
				throw ex2;
			}
			finally
			{
				if (userId != null)
				{
					connection.commit();
				}
			}
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	private <T> List<T> execute(
		Connection connection,
		String functionName,
		List<SqlFunctionParameter> inParameters,
		RowMapper<T> rowMapper) throws SQLException
	{
		String sql = createFunctionCall(functionName, inParameters);
		try (CallableStatement callableStatement = connection.prepareCall(sql))
		{
			setParameters(callableStatement, inParameters);

			try (ResultSet resultSet = callableStatement.executeQuery())
			{
				if (callableStatement.getWarnings() != null)
				{
					throw new SqlDaoException(callableStatement.getWarnings());
				}

				List<T> results = new ArrayList<>();
				while (resultSet.next())
				{
					results.add(rowMapper.getValue(resultSet));
				}
				return results;
			}
		}
	}

	private void setUserId(int userId, Connection connection)
		throws SQLException
	{
		SqlFunctionParameter<Integer>
			userIdParameter
			= new SqlFunctionParameter<>("id", userId, Types.INTEGER);
		List<Integer> setUserIds = this.execute(connection,
			SET_USER_ID_FUNCTION_NAME,
			Collections.singletonList(userIdParameter),
			rs -> rs.getInt(SET_USER_ID_FUNCTION_NAME));

		if (setUserIds == null
			|| setUserIds.size() != 1 && setUserIds.get(0) != userId)
		{
			throw new SqlDaoException(String.format(
				"Setting the user id to %d actually set it ot %s.",
				userId,
				setUserIds));
		}
	}
}
