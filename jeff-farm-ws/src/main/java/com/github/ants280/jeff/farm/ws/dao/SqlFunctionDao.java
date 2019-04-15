package com.github.ants280.jeff.farm.ws.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;

public class SqlFunctionDao
{
	private final DataSource dataSource;
	// TODO: pull "jeff_farm_db" from ${jdbc.database} maven property
	private static final String USER_ID = "jeff_farm_db.user_id";

	public SqlFunctionDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public void executeUpdate(
			String storedProcedureName,
			List<Parameter> inParameters,
			int userId)
	{
		this.setUserId(userId);

		String sql = createFunctionCall(storedProcedureName, inParameters);

		try (Connection connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(sql))
		{
			setParameters(callableStatement, inParameters);

			boolean resultSetProduced = callableStatement.execute();
			assert !resultSetProduced;

			if (callableStatement.getWarnings() != null)
			{
				throw new SqlDaoException(callableStatement.getWarnings());
			}

			if ((int) callableStatement.getUpdateCount() != 1)
			{
				throw new SqlDaoException(String.format(
					"Did not updated 1 item.  Updated %d items.",
					callableStatement.getUpdateCount()));
			}
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	public int executeCreate(
			String storedProcedureName,
			List<Parameter> inParameters,
			String outParameterName,
			int userId)
	{
		this.setUserId(userId);

		String sql = createFunctionCall(storedProcedureName, inParameters);

		try (Connection connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(sql))
		{
			setParameters(callableStatement, inParameters);

			boolean resultSetProduced = callableStatement.execute();
			assert !resultSetProduced;

			if (callableStatement.getWarnings() != null)
			{
				throw new SqlDaoException(callableStatement.getWarnings());
			}

			if ((int) callableStatement.getUpdateCount() != 1)
			{
				throw new SqlDaoException(String.format(
					"Did not create 1 item.  Created %s items.",
					callableStatement.getUpdateCount()));
			}

			return callableStatement.getInt(outParameterName);
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	private static String createFunctionCall(
		String functionName,
		List<Parameter> inParameters)
	{
		return String.format("SELECT %s(%s)",
			functionName,
			inParameters.stream()
				.map(parameter -> "?")
				.collect(Collectors.joining(", ")));
	}

	private static void setParameters(
		PreparedStatement preparedStatement,
		List<Parameter> inParameters)
		throws SQLException
	{
		int index = 1;
		for (Parameter parameter : inParameters)
		{
			setParameter(preparedStatement, parameter, index++);
		}
	}

	private static void setParameter(
		PreparedStatement preparedStatement,
		Parameter parameter,
		int index)
		throws SQLException
	{
		// TODO: use map
		switch (parameter.getSqlType())
		{
			case Types.VARCHAR:
			case Types.CHAR:
				preparedStatement.setString(index, (String) parameter.getValue());
				break;
			case Types.INTEGER:
			case Types.BIT:
				preparedStatement.setInt(index, (int) parameter.getValue());
			default:
				throw new IllegalArgumentException(
					"Cannot set parameter of type " + parameter.getSqlType());
		}
	}

	public boolean executeReadBoolean(
			String storedProcedureName,
			List<Parameter> inParameters,
			String outParameterName)
	{
		String sql = createFunctionCall(storedProcedureName, inParameters);

		try (Connection connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(sql))
		{
			setParameters(callableStatement, inParameters);

			boolean resultSetProduced = callableStatement.execute();
			assert !resultSetProduced;

			if (callableStatement.getWarnings() != null)
			{
				throw new SqlDaoException(callableStatement.getWarnings());
			}

			if ((int) callableStatement.getUpdateCount() != 1)
			{
				throw new SqlDaoException(String.format(
					"Only expected one row.  Got %d",
					callableStatement.getUpdateCount()));
			}

			return callableStatement.getBoolean(outParameterName);
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	public <T> T executeRead(
			String storedProcedureName,
			List<Parameter> inParameters,
			RowMapper<T> rowMapper)
	{
		String sql = createFunctionCall(storedProcedureName, inParameters);

		try (Connection connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(sql))
		{
			setParameters(callableStatement, inParameters);

			boolean resultSetProduced = callableStatement.execute();
			assert resultSetProduced;

			if (callableStatement.getWarnings() != null)
			{
				throw new SqlDaoException(callableStatement.getWarnings());
			}

			if ((int) callableStatement.getUpdateCount() != 1)
			{
				throw new SqlDaoException(String.format(
					"Updated %d rows during a read.  Should not have.",
					callableStatement.getUpdateCount()));
			}

			ResultSet resultSet = callableStatement.getResultSet();
			T value = rowMapper.getValue(resultSet);

			assert !resultSet.next();
			assert !callableStatement.getMoreResults();

			return value;
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	public <T> List<T> executeReadList(
			String storedProcedureName,
			List<Parameter> inParameters,
			RowMapper<T> rowMapper)
	{
		String sql = createFunctionCall(storedProcedureName, inParameters);

		try (Connection connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(sql))
		{
			setParameters(callableStatement, inParameters);

			boolean resultSetProduced = callableStatement.execute();
			assert resultSetProduced;

			if (callableStatement.getWarnings() != null)
			{
				throw new SqlDaoException(callableStatement.getWarnings());
			}

			if ((int) callableStatement.getUpdateCount() != 1)
			{
				throw new SqlDaoException(String.format(
					"Updated %d rows during a read.  Should not have.",
					callableStatement.getUpdateCount()));
			}

			ResultSet resultSet = callableStatement.getResultSet();
			List<T> values = new ArrayList<>();
			do
			{
				values.add(rowMapper.getValue(resultSet));
			}
			while(resultSet.next());

			assert !callableStatement.getMoreResults();

			return values;
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	private void setUserId(int userId)
	{
		// TODO: Write function for setting user id
		String sql = String.format("SET %s = ?", USER_ID);

		try (Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, userId);

			boolean resultSetProduced = preparedStatement.execute();
			assert !resultSetProduced;

			if (preparedStatement.getWarnings() != null)
			{
				throw new SqlDaoException(preparedStatement.getWarnings());
			}

			if ((int) preparedStatement.getUpdateCount() != 1)
			{
				throw new SqlDaoException(String.format(
					"Updated %d rows during SET call.  Should not have.",
					preparedStatement.getUpdateCount()));
			}
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	public static class Parameter<T>
	{
		private final String name;
		private final T value;
		private final int sqlType;

		public Parameter(String name, T value, int sqlType)
		{
			this.name = name;
			this.value = value;
			this.sqlType = sqlType;
		}

		public String getName()
		{
			return name;
		}

		public T getValue()
		{
			return value;
		}

		public int getSqlType()
		{
			return sqlType;
		}

		@Override
		public String toString()
		{
			return String.format(
					"Parameter{name=%s,value=%s,sqlType=%d}",
					name,
					value,
					sqlType);
		}
	}
}
