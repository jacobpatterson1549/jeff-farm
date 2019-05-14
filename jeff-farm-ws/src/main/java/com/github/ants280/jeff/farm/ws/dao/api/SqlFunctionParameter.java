package com.github.ants280.jeff.farm.ws.dao.api;

public class SqlFunctionParameter<T>
{
	private final String name;
	private final T value;
	private final int sqlType;

	public SqlFunctionParameter(String name, T value, int sqlType)
	{
		this.name = name;
		this.value = value;
		this.sqlType = sqlType;
	}

	public String getName()
	{
		return name;
	}

	public T getValue()
	{
		return value;
	}

	public int getSqlType()
	{
		return sqlType;
	}

	@Override
	public String toString()
	{
		return String.format(
			"SqlFunctionParameter{name=%s,value=%s,sqlType=%d}",
			name,
			value,
			sqlType);
	}
}
