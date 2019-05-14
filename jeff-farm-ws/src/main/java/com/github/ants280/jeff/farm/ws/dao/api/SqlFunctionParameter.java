package com.github.ants280.jeff.farm.ws.dao.api;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SqlFunctionParameter<T> // TODO: it would be nice if this was not abstract
{
	private final String name;
	private final T value;
	private final PreparedStatementIndexSetter<T> preparedStatementIndexSetter;

	protected SqlFunctionParameter(String name, T value, PreparedStatementIndexSetter<T> preparedStatementIndexSetter)
	{
		this.name = name;
		this.value = value;
		this.preparedStatementIndexSetter = preparedStatementIndexSetter;
	}

	public String getName()
	{
		return name;
	}

	public void setValue(PreparedStatement preparedStatement, int index)
		throws SQLException
	{
		preparedStatementIndexSetter.setAtIndex(preparedStatement, index, value);
	}

	@Override
	public String toString()
	{
		return String.format("%s{name=%s,value=%s",
			this.getClass().getSimpleName(),
			name,
			value);
	}

	protected interface PreparedStatementIndexSetter<T>
	{
		void setAtIndex(PreparedStatement ps, int index, T value)
			throws SQLException;
	}
}
