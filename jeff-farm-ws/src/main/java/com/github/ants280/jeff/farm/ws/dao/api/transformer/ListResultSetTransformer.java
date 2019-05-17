package com.github.ants280.jeff.farm.ws.dao.api.transformer;

import com.github.ants280.jeff.farm.ws.dao.api.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListResultSetTransformer<T>
	implements ResultSetTransformer<List<T>>
{
	private final RowMapper<T> crudItemRowMapper;

	public ListResultSetTransformer(RowMapper<T> rowMapper)
	{
		this.crudItemRowMapper = rowMapper;
	}

	@Override
	public List<T> transform(ResultSet resultSet) throws SQLException
	{
		List<T> results = new ArrayList<>();
		while (resultSet.next())
		{
			results.add(crudItemRowMapper.getValue(resultSet));
		}

		return results;
	}
}
