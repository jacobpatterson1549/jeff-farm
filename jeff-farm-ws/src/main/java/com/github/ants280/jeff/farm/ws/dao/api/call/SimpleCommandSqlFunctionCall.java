package com.github.ants280.jeff.farm.ws.dao.api.call;

import com.github.ants280.jeff.farm.ws.dao.api.SqlDaoException;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.ResultSetTransformer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SimpleCommandSqlFunctionCall<T> extends SqlFunctionCall<T>
{
	private final List<SqlFunctionParameter> inParameters;
	private final ResultSetTransformer<T> resultSetTransformer;
	private T result;
	private boolean resultsSet;

	public SimpleCommandSqlFunctionCall(
		String functionCallSql,
		List<SqlFunctionParameter> inParameters,
		ResultSetTransformer<T> resultSetTransformer)
	{
		super(functionCallSql, inParameters.size());
		this.inParameters = inParameters;
		this.resultSetTransformer = resultSetTransformer;
		this.result = null;
		this.resultsSet = false;
	}

	@Override
	public void execute(PreparedStatement preparedStatement)
		throws SQLException
	{
		this.setParameters(preparedStatement, inParameters);

		if (preparedStatement.execute() && resultSetTransformer != null)
		{
			try (ResultSet resultSet = preparedStatement.getResultSet())
			{
				if (preparedStatement.getWarnings() != null)
				{
					throw new SqlDaoException(preparedStatement.getWarnings());
				}

				result = resultSetTransformer.transform(resultSet);
			}
		}

		resultsSet = true;
	}

	@Override
	public T getResult()
	{
		if (!resultsSet)
		{
			throw new IllegalArgumentException("Call has not been executed");
		}

		return result;
	}
}
