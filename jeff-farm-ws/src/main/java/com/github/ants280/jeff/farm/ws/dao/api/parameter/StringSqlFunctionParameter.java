package com.github.ants280.jeff.farm.ws.dao.api.parameter;

import java.sql.PreparedStatement;

public class StringSqlFunctionParameter extends SqlFunctionParameter<String>
{
	public StringSqlFunctionParameter(String name, String value)
	{
		super(name, value, PreparedStatement::setString);
	}
}
