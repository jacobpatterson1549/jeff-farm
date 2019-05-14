package com.github.ants280.jeff.farm.ws.dao.api;

import com.github.ants280.jeff.farm.ws.model.CrudItem;
import com.github.ants280.jeff.farm.ws.model.CrudItemGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CrudItemGroupResultSetTransformer<V extends CrudItem, T extends CrudItemGroup<V, T>>
	implements ResultSetTransformer<T>
{
	private final boolean expectSingleRecord;
	private final RowMapper<T> crudItemGroupRowMapper;
	private final RowMapper<V> crudItemRowMapper;
	private final Function<V, Integer> groupIdMappingFunction;
	private final List<T> crudItemGroups;
	private final List<V> crudItems;
	private int resultSetsTransformed;

	public CrudItemGroupResultSetTransformer(
		boolean expectSingleRecord,
		RowMapper<T> crudItemGroupRowMapper,
		RowMapper<V> crudItemRowMapper,
		Function<V, Integer> groupIdMappingFunction)
	{
		this.expectSingleRecord = expectSingleRecord;
		this.crudItemGroupRowMapper = crudItemGroupRowMapper;
		this.crudItemRowMapper = crudItemRowMapper;
		this.groupIdMappingFunction = groupIdMappingFunction;
		this.crudItemGroups = new ArrayList<>();
		this.crudItems = new ArrayList<>();
		this.resultSetsTransformed = 0;
	}

	@Override
	public void accept(ResultSet resultSet) throws SQLException
	{
		switch (resultSetsTransformed)
		{
			case 0:
				while (resultSet.next())
				{
					crudItemGroups.add(crudItemGroupRowMapper.getValue(resultSet));
				}
				break;
			case 1:
				while (resultSet.next())
				{
					crudItems.add(crudItemRowMapper.getValue(resultSet));
				}
				break;
			default:
				throw new SqlDaoException(
					"Too many ResultSets transformed.  Expected 2.");
		}
		resultSetsTransformed++;
	}

	@Override
	public List<T> getResults()
	{
		if (resultSetsTransformed != 2)
		{
			throw new SqlDaoException("Not enough ResultSets transformed.  "
				+ "Expected 2, but got "
				+ resultSetsTransformed);
		}
		if (expectSingleRecord && crudItemGroups.size() != 1)
		{
			throw new SqlDaoException(String.format("Expected single record, but got %d.",
				crudItemGroups.size()));
		}

		Map<Integer, T> crudItemGroupsById = crudItemGroups.stream()
			.collect(Collectors.toMap(CrudItemGroup::getId,
				Function.identity()));
		Map<Integer, List<V>> crudItemsByGroupId = crudItems.stream()
			.collect(Collectors.groupingBy(groupIdMappingFunction));
		if (crudItemGroups.size() != crudItemsByGroupId.size()
			|| crudItemGroupsById.keySet()
			.containsAll(crudItemsByGroupId.keySet()))
		{
			throw new SqlDaoException(String.format(
				"Got CrudItemGroups %s, but got CrudItems for groups %s",
				Arrays.toString(crudItemGroupsById.keySet().toArray()),
				Arrays.toString(crudItemsByGroupId.keySet().toArray())));
		}

		crudItemGroupsById.forEach((id, crudItemGroup) -> crudItemGroup.setItems(
			crudItemsByGroupId.get(id)));

		return crudItemGroups;
	}
}
