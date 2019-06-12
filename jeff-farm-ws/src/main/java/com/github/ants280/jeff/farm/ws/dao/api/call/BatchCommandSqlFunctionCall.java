package com.github.ants280.jeff.farm.ws.dao.api.call;

import com.github.ants280.jeff.farm.ws.dao.api.SqlDaoException;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BatchCommandSqlFunctionCall extends SqlFunctionCall<Void>
{
	private final List<List<SqlFunctionParameter>> inParametersList;

	public BatchCommandSqlFunctionCall(
		String functionCallSql,
		List<List<SqlFunctionParameter>> inParametersList)
	{
		super(
			functionCallSql,
			inParametersList.isEmpty() ? 0 : inParametersList.get(0).size());
		this.inParametersList = inParametersList;
	}

	@Override
	public void execute(PreparedStatement preparedStatement)
		throws SQLException
	{
		if (inParametersList.isEmpty())
		{
			return; // Nothing to do.
		}

		for (List<SqlFunctionParameter> inParameters : inParametersList)
		{
			this.setParameters(preparedStatement, inParameters);
			preparedStatement.addBatch();
		}

		int[] updateCounts = preparedStatement.executeBatch();

		if (updateCounts.length != inParametersList.size())
		{
			throw new SqlDaoException(String.format(
				"Did not perform expected amount of updates.  "
					+ "Expected %d, got %d",
				inParametersList.size(),
				updateCounts.length));
		}
		// not checking the update counts because Postgres returns 0 for each batch executed
	}

	@Override
	public Void getResult()
	{
		return null;
	}
}
