package com.github.ants280.jeff.farm.ws.dao.api;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import javax.sql.DataSource;

public class SqlFunctionDao
{
	private final DataSource dataSource;

	public SqlFunctionDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public <A, B, C> C execute(
		BiFunction<A, B, C> resultFunction,
		SqlFunctionCall<A> functionCallA,
		SqlFunctionCall<B> functionCallB)
	{
		return this.execute(() -> resultFunction.apply(functionCallA.getResult(),
			functionCallB.getResult()),
			new SqlFunctionCall[]{functionCallA, functionCallB});
	}

	public <A> A execute(SqlFunctionCall<A> functionCall)
	{
		return this.execute(functionCall::getResult,
			new SqlFunctionCall[]{functionCall});
	}

	public void execute(SqlFunctionCall... functionCalls)
	{
		this.execute(() -> null, functionCalls);
	}

	private <T> T execute(
		Supplier<T> resultSupplier, SqlFunctionCall<?>[] functionCalls)
	{
		try (Connection connection = dataSource.getConnection())
		{
			return execute(connection, resultSupplier, functionCalls);
		}
		catch (SQLException ex)
		{
			throw new JeffFarmWsException(ex);
		}
	}

	private <T> T execute(
		Connection connection,
		Supplier<T> resultSupplier,
		SqlFunctionCall<?>[] functionCalls) throws SQLException
	{
		try
		{
			connection.setAutoCommit(false);
			return this.executeCalls(connection, resultSupplier, functionCalls);
		}
		catch (SQLException ex)
		{
			connection.rollback();
			throw ex;
		}
		finally
		{
			connection.commit();
		}
	}

	private <T> T executeCalls(
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
}
