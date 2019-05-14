package com.github.ants280.jeff.farm.ws.dao.api;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BatchCommandSqlFunctionCall<T> extends SqlFunctionCall<T>
{
	private final List<List<SqlFunctionParameter>> inParametersList;

	public BatchCommandSqlFunctionCall(
		String functionCallSql,
		List<List<SqlFunctionParameter>> inParametersList)
	{
		super(functionCallSql, inParametersList.get(0).size());
		this.inParametersList = inParametersList;
	}

	@Override
	public void setParameters(PreparedStatement preparedStatement)
		throws SQLException
	{
		for (List<SqlFunctionParameter> inParameters : inParametersList)
		{
			this.setParameters(preparedStatement, inParameters);
			preparedStatement.addBatch();
		}
	}

	@Override
	public List<T> execute(PreparedStatement preparedStatement)
		throws SQLException
	{
		int[] updateCounts = preparedStatement.executeBatch();

		if (updateCounts.length != inParametersList.size())
		{
			throw new SqlDaoException(String.format(
				"Did not perform expected amount of updates.  "
					+ "Expected %d, got %d",
				inParametersList.size(),
				updateCounts.length));
		}
		for (int i = 0; i < updateCounts.length; i++)
		{
			if (updateCounts[i] != 1)
			{
				throw new SqlDaoException(String.format("Expected to only update one row for batch item %d.  "
						+ "Actually updated %d rows",
					i,
					updateCounts[i]));
			}
		}

		return Collections.emptyList();
	}
}
