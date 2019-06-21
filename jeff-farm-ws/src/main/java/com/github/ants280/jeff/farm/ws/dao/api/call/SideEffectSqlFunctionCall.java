package com.github.ants280.jeff.farm.ws.dao.api.call;

import com.github.ants280.jeff.farm.ws.dao.UserIdDao;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.ResultSetTransformer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

/**
 * A SimpleCommandSqlFunctionCall that lets the result get consumed before
 * finishing execution.  Useful for checking/adjusting state between calls.
 */
public class SideEffectSqlFunctionCall<T> extends SimpleCommandSqlFunctionCall<T>
{
	private final Consumer<T> resultConsumer;

	public SideEffectSqlFunctionCall(
		String functionName,
		List<SqlFunctionParameter> inParameters,
		ResultSetTransformer<T> resultSetTransformer,
		Consumer<T> resultConsumer,
		UserIdDao userIdDao)
	{
		super(
			functionName,
			inParameters,
			resultSetTransformer,
			userIdDao);
		this.resultConsumer = resultConsumer;
	}

	@Override
	public void execute(PreparedStatement preparedStatement)
		throws SQLException
	{
		super.execute(preparedStatement);
		T result = this.getResult();
		resultConsumer.accept(result);
	}
}
