package com.github.ants280.jeff.farm.ws.dao.api.parameter;

import java.sql.PreparedStatement;

public class BooleanSqlFunctionParameter extends SqlFunctionParameter<Boolean>
{
	public BooleanSqlFunctionParameter(String name, Boolean value)
	{
		super(name, value, PreparedStatement::setBoolean);
	}
}
