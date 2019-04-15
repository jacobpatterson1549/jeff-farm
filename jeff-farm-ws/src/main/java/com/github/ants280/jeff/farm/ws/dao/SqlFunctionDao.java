package com.github.ants280.jeff.farm.ws.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
			String functionName,
			List<Parameter> inParameters,
			int userId)
	{
		this.setUserId(userId);

		this.executeSingle(
			functionName,
			inParameters,
			resultSet -> (Void) null);
	}

	public int executeCreate(
			String functionName,
			List<Parameter> inParameters,
			String outParameterName,
			int userId)
	{
		this.setUserId(userId);

		return this.executeSingle(
			functionName,
			inParameters,
			resultSet -> resultSet.getInt(outParameterName));
	}

	public boolean executeReadBoolean(
			String functionName,
			List<Parameter> inParameters,
			String outParameterName)
	{
		return this.executeSingle(
			functionName,
			inParameters,
			resultSet -> resultSet.getBoolean(outParameterName));
	}

	public <T> T executeRead(
			String functionName,
			List<Parameter> inParameters,
			RowMapper<T> rowMapper)
	{
		return this.executeSingle(functionName, inParameters, rowMapper);
	}

	public <T> List<T> executeReadList(
			String functionName,
			List<Parameter> inParameters,
			RowMapper<T> rowMapper)
	{
		return this.execute(functionName, inParameters, rowMapper);
	}

	private <T> T executeSingle(
			String functionName,
			List<Parameter> inParameters,
			RowMapper<T> rowMapper)
	{
		List<T> results = this.execute(functionName, inParameters, rowMapper);

		if (results.size() != 1)
		{
			throw new SqlDaoException(String.format(
				"Expected 1 result.  Get %d.",
				results.size()));
		}

		return results.get(0);
	}


	private <T> List<T> execute(
			String functionName,
			List<Parameter> inParameters,
			RowMapper<T> rowMapper)
	{
		String sql = createFunctionCall(functionName, inParameters);

		try (Connection connection = dataSource.getConnection();
			CallableStatement callableStatement = connection.prepareCall(sql))
		{
			setParameters(callableStatement, inParameters);

			ResultSet resultSet = callableStatement.executeQuery();

			// TODO: stream
			List<T> results = new ArrayList<>();
			while (resultSet.next())
			{
				results.add(rowMapper.getValue(resultSet));
			}

			return results;
		}
		catch (SQLException ex)
		{
			throw new SqlDaoException(ex);
		}
	}

	private void setUserId(int userId)
	{
		// TODO: Write function for setting user id
		// TODO: Could this lead to sql injection?
		String sql = String.format("SET %s = %d", USER_ID, userId);

		try (Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement())
		{
			boolean resultSetProduced = statement.execute(sql);
			assert !resultSetProduced;

			if (statement.getWarnings() != null)
			{
				throw new SqlDaoException(statement.getWarnings());
			}

			if (statement.getUpdateCount() != 0)
			{
				throw new SqlDaoException(String.format(
					"Updated %d rows during SET call.  Should not have.",
					statement.getUpdateCount()));
			}
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
		return String.format("SELECT * FROM %s(%s)",
			functionName,
			String.join(", ", Collections.nCopies(inParameters.size(), "?")));
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
				preparedStatement.setInt(index, (int) parameter.getValue());
				break;
			default:
				throw new IllegalArgumentException(
					"Cannot set parameter of type " + parameter.getSqlType());
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
