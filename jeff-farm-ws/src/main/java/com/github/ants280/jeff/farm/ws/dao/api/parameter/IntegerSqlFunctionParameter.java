package com.github.ants280.jeff.farm.ws.dao.api.parameter;

import java.sql.PreparedStatement;

public class IntegerSqlFunctionParameter extends SqlFunctionParameter<Integer>
{
	public IntegerSqlFunctionParameter(String name, Integer value)
	{
		super(name, value, PreparedStatement::setInt);
	}
}
