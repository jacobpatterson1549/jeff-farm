package com.github.ants280.jeff.farm.ws.dao.api.call;

import com.github.ants280.jeff.farm.ws.dao.LoginDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public abstract class SqlFunctionCall<T>
{
	private final String functionCallSql;
	private final int numParameters;
	@Inject
	private LoginDao loginDao;

	public SqlFunctionCall(String functionCallSql, int numParameters)
	{
		this.functionCallSql = functionCallSql;
		this.numParameters = numParameters + 1;
	}

	public String getFunctionCallSql()
	{
		return String.format(
			"SELECT * FROM %s(%s)",
			functionCallSql,
			String.join(", ", Collections.nCopies(numParameters, "?")));
	}

	protected void setParameters(
		PreparedStatement preparedStatement,
		List<SqlFunctionParameter> inParameters) throws SQLException
	{
		preparedStatement.setInt(1, loginDao.getUserId());
		int index = 2;
		for (SqlFunctionParameter inParameter : inParameters)
		{
			inParameter.setValue(preparedStatement, index++);
		}
	}

	public abstract void execute(PreparedStatement preparedStatement)
		throws SQLException;

	public abstract T getResult();
}
