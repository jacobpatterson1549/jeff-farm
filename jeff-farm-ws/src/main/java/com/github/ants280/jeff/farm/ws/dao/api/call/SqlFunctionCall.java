package com.github.ants280.jeff.farm.ws.dao.api.call;

import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public abstract class SqlFunctionCall<T>
{
	private final String functionCallSql;
	private final int numParameters;

	public SqlFunctionCall(String functionCallSql, int numParameters)
	{
		this.functionCallSql = functionCallSql;
		this.numParameters = numParameters;
	}

	public String getFunctionCallSql()
	{
		return String.format(
			"SELECT * FROM %s(%s)",
			functionCallSql,
			String.join(", ", Collections.nCopies(numParameters, "?")));
	}

	public abstract void setParameters(PreparedStatement preparedStatement)
		throws SQLException;

	protected void setParameters(
		PreparedStatement preparedStatement,
		List<SqlFunctionParameter> inParameters) throws SQLException
	{
		int index = 1;
		for (SqlFunctionParameter inParameter : inParameters)
		{
			inParameter.setValue(preparedStatement, index++);
		}
	}

	public abstract List<T> execute(PreparedStatement preparedStatement)
		throws SQLException;
}
