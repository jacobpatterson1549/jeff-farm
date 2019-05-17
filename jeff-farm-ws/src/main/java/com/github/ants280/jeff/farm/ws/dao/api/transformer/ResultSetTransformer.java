package com.github.ants280.jeff.farm.ws.dao.api.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetTransformer<T>
{
	T transform(ResultSet resultSet) throws SQLException;
}
