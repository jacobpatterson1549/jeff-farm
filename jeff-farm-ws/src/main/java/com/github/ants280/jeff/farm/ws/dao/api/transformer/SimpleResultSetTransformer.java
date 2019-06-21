package com.github.ants280.jeff.farm.ws.dao.api.transformer;

import com.github.ants280.jeff.farm.ws.dao.api.RowMapper;
import com.github.ants280.jeff.farm.ws.dao.api.SqlDaoException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleResultSetTransformer<T> implements ResultSetTransformer<T>
{
	private final RowMapper<T> crudItemRowMapper;

	public SimpleResultSetTransformer(RowMapper<T> rowMapper)
	{
		this.crudItemRowMapper = rowMapper;
	}

	@Override
	public T transform(ResultSet resultSet) throws SQLException
	{
		if (!resultSet.next())
		{
			return null;
		}

		T result = crudItemRowMapper.getValue(resultSet);

		if (resultSet.next())
		{
			throw new SqlDaoException("Expected single ResultsSet, got more");
		}

		return result;
	}
}
