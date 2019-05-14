package com.github.ants280.jeff.farm.ws.dao.api.call;

import com.github.ants280.jeff.farm.ws.dao.api.transformer.ResultSetTransformer;
import com.github.ants280.jeff.farm.ws.dao.api.SqlDaoException;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SingleCommandSqlFunctionCall<T> extends SqlFunctionCall<T>
{
	private final List<SqlFunctionParameter> inParameters;
	private final ResultSetTransformer<T> resultSetTransformer;

	public SingleCommandSqlFunctionCall(
		String functionCallSql,
		List<SqlFunctionParameter> inParameters,
		ResultSetTransformer<T> resultSetTransformer)
	{
		super(functionCallSql, inParameters.size());
		this.inParameters = inParameters;
		this.resultSetTransformer = resultSetTransformer;
	}

	@Override
	public void setParameters(PreparedStatement preparedStatement)
		throws SQLException
	{
		this.setParameters(preparedStatement, inParameters);
	}

	@Override
	public List<T> execute(PreparedStatement preparedStatement)
		throws SQLException
	{
		if (preparedStatement.execute())
		{
			do
			{
				resultSetTransformer.accept(preparedStatement.getResultSet());
			}
			while (preparedStatement.getMoreResults());
		}

		if (preparedStatement.getWarnings() != null)
		{
			throw new SqlDaoException(preparedStatement.getWarnings());
		}

		return resultSetTransformer.getResults();
	}
}
