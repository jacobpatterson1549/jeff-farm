package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.Farm;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;

@Service
public class FarmDao extends StoredProcedureDao implements CrudDao<Farm>
{
	@Inject
	public FarmDao(DataSource dataSource)
	{
		super(dataSource);
	}

	@Override
	public int create(Farm farm)
	{
		return this.executeCreate(
				"create_farm",
				Arrays.asList(
						new Parameter(Farm.NAME_COLUMN, farm.getName(), Types.VARCHAR),
						new Parameter(Farm.LOCATION_COLUMN, farm.getLocation(), Types.VARCHAR)),
				Farm.ID_COLUMN);
	}

	@Override
	public Farm read(int id)
	{
		return this.executeRead(
				"read_farm",
				Collections.singletonList(
						new Parameter(Farm.ID_COLUMN, id, Types.INTEGER)),
				new Farm.ResultSetExtractor());
	}

	@Override
	public List<Farm> readList(int parentId)
	{
		return this.executeReadList(
				"read_farms",
				Collections.emptyList(),
				new Farm.ResultSetExtractor());
	}

	@Override
	public void update(Farm farm)
	{
		this.executeUpdate(
				"update_farm",
				Arrays.asList(
						new Parameter(Farm.ID_COLUMN, farm.getId(), Types.INTEGER),
						new Parameter(Farm.NAME_COLUMN, farm.getName(), Types.VARCHAR),
						new Parameter(Farm.LOCATION_COLUMN, farm.getLocation(), Types.VARCHAR)));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_farm",
				Collections.singletonList(
						new Parameter(Farm.ID_COLUMN, id, Types.INTEGER)));
	}
}
