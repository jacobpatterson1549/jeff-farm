package com.github.ants280.jeff.farm.ws.dao.api;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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

	public String getFunctionCallSql()
	{
		return String.format("SELECT * FROM %s(%s)",
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
		for (SqlFunctionParameter parameter : inParameters)
		{
			setParameter(preparedStatement, parameter, index++);
		}
	}

	public abstract List<T> execute(PreparedStatement preparedStatement) throws SQLException;
}
