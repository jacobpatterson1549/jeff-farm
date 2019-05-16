package com.github.ants280.jeff.farm.ws.dao.api.call;

import com.github.ants280.jeff.farm.ws.dao.api.SqlDaoException;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.ResultSetTransformer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				ResultSet resultSet = preparedStatement.getResultSet();
				resultSetTransformer.accept(resultSet);
			}
			while (preparedStatement.getMoreResults()); // TODO: this always will return false with the current structure.
		}

		if (preparedStatement.getWarnings() != null)
		{
			throw new SqlDaoException(preparedStatement.getWarnings());
		}

		return resultSetTransformer.getResults();
	}
}
