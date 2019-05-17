package com.github.ants280.jeff.farm.ws.dao.api;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.dao.api.call.SimpleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.SimpleResultSetTransformer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import javax.sql.DataSource;

public class SqlFunctionDao
{
	public static final String SET_USER_ID_FUNCTION_NAME = "set_user_id";
	private final DataSource dataSource;

	public SqlFunctionDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public <A, B, C> C execute(
		Integer userId,
		BiFunction<A, B, C> resultFunction,
		SqlFunctionCall<A> functionCallA,
		SqlFunctionCall<B> functionCallB)
	{
		return this.execute(userId,
			() -> resultFunction.apply(functionCallA.getResult(),
				functionCallB.getResult()),
			new SqlFunctionCall[]{functionCallA, functionCallB});
	}

	public <A> A execute(
		Integer userId, SqlFunctionCall<A> functionCall)
	{
		return this.execute(userId,
			functionCall::getResult,
			new SqlFunctionCall[]{functionCall});
	}

	public void execute(
		Integer userId, SqlFunctionCall... functionCalls)
	{
		this.execute(userId, () -> null, functionCalls);
	}

	private <T> T execute(
		Integer userId,
		Supplier<T> resultSupplier,
		SqlFunctionCall<?>[] functionCalls)
	{
		try (Connection connection = dataSource.getConnection())
		{
			return execute(connection, userId, resultSupplier, functionCalls);
		}
		catch (SQLException ex)
		{
			throw new JeffFarmWsException(ex);
		}
	}

	private <T> T execute(
		Connection connection,
		Integer userId,
		Supplier<T> resultSupplier,
		SqlFunctionCall<?>[] functionCalls) throws SQLException
	{
		try
		{
			if (userId != null)
			{
				connection.setAutoCommit(false);
				this.setUserId(userId, connection);
			}

			return this.execute(connection, resultSupplier, functionCalls);
		}
		catch (SQLException ex)
		{
			if (userId != null)
			{
				connection.rollback();
			}
			throw ex;
		}
		finally
		{
			if (userId != null)
			{
				connection.commit();
			}
		}
	}

	private <T> T execute(
		Connection connection,
		Supplier<T> resultSupplier,
		SqlFunctionCall<?>... sqlFunctionCalls) throws SQLException
	{
		for (SqlFunctionCall<?> sqlFunctionCall : sqlFunctionCalls)
		{
			execute(connection, sqlFunctionCall);
		}

		return resultSupplier.get();
	}

	private void execute(
		Connection connection, SqlFunctionCall<?> sqlFunctionCall)
		throws SQLException
	{
		String sql = sqlFunctionCall.getFunctionCallSql();
		try (
			PreparedStatement preparedStatement = connection.prepareStatement(
				sql))
		{
			sqlFunctionCall.execute(preparedStatement);
		}
	}

	private void setUserId(int userId, Connection connection)
		throws SQLException
	{
		SqlFunctionParameter<Integer>
			userIdParameter
			= new IntegerSqlFunctionParameter("id", userId);
		SqlFunctionCall<Integer>
			sqlFunctionCall
			= new SimpleCommandSqlFunctionCall<>(SET_USER_ID_FUNCTION_NAME,
			Collections.singletonList(userIdParameter),
			new SimpleResultSetTransformer<>(resultSet -> resultSet.getInt(
				SET_USER_ID_FUNCTION_NAME)));

		Integer setUserId = this.execute(connection,
			sqlFunctionCall::getResult,
			sqlFunctionCall);

		if (setUserId == null || setUserId != userId)
		{
			throw new SqlDaoException(String.format(
				"Setting the user id to %d actually set it ot %s.",
				userId,
				setUserId));
		}
	}
}
