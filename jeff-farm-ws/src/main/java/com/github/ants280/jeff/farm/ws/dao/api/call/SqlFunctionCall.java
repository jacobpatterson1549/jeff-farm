package com.github.ants280.jeff.farm.ws.dao.api.call;

import com.github.ants280.jeff.farm.ws.dao.UserIdDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

@Provider
public abstract class SqlFunctionCall<T>
{
	private final String functionCallSql;
	private final int numParameters;
	@Inject
	private UserIdDao userIdDao;

	protected SqlFunctionCall(String functionCallSql, int numParameters, UserIdDao userIdDao)
	{
		this.functionCallSql = functionCallSql;
		this.numParameters = numParameters;
		this.userIdDao = userIdDao;
	}

	public String getFunctionCallSql()
	{
		return String.format(
			"SELECT * FROM %s(%s)",
			functionCallSql,
			String.join(", ", Collections.nCopies(
				(userIdDao == null ? numParameters : numParameters + 1), "?")));
	}

	protected void setParameters(
		PreparedStatement preparedStatement,
		List<SqlFunctionParameter<?>> inParameters) throws SQLException
	{
		int index = 1;
		if (userIdDao != null)
		{
			preparedStatement.setInt(index++, userIdDao.getUserId());
		}
		for (SqlFunctionParameter<?> inParameter : inParameters)
		{
			inParameter.setValue(preparedStatement, index++);
		}
	}

	public abstract void execute(PreparedStatement preparedStatement)
		throws SQLException;

	public abstract T getResult();
}
