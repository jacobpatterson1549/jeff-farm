package com.github.ants280.jeff.farm.ws.dao.api.parameter;

import java.sql.PreparedStatement;

public class DoubleSqlFunctionParameter extends SqlFunctionParameter<Double>
{
	public DoubleSqlFunctionParameter(String name, Double value)
	{
		super(name, value, PreparedStatement::setDouble);
	}
}
