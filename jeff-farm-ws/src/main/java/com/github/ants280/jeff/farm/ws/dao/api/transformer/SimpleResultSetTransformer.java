package com.github.ants280.jeff.farm.ws.dao.api.transformer;

import com.github.ants280.jeff.farm.ws.dao.api.RowMapper;
import com.github.ants280.jeff.farm.ws.dao.api.SqlDaoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleResultSetTransformer<T> implements ResultSetTransformer<T>
{
	private final boolean expectSingleRecord;
	private final RowMapper<T> crudItemRowMapper;
	private final List<T> results;
	private boolean resultSetTransformed;

	public SimpleResultSetTransformer(boolean expectSingleRecord, RowMapper<T> crudItemRowMapper)
	{
		this.expectSingleRecord = expectSingleRecord;
		this.crudItemRowMapper = crudItemRowMapper;
		this.results = new ArrayList<>();
		this.resultSetTransformed = false;
	}

	@Override
	public void accept(ResultSet resultSet) throws SQLException
	{
		if (resultSetTransformed)
		{
			throw new SqlDaoException("ResultSet already transformed.");
		}

		while (resultSet.next())
		{
			if (crudItemRowMapper != null)
			{
				results.add(crudItemRowMapper.getValue(resultSet));
			}
		}

		resultSetTransformed = true;
	}

	@Override
	public List<T> getResults()
	{
		if (!resultSetTransformed)
		{
			throw new SqlDaoException("ResultSet not transformed.");
		}
		if (expectSingleRecord && results.size() != 1)
		{
			throw new SqlDaoException(String.format("Expected single record, but got %d.",
				results.size()));
		}

		return results;
	}
}
