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
				"createFarm",
				Arrays.asList(
						new Parameter("farmName", farm.getName(), Types.VARCHAR),
						new Parameter("farmLocation", farm.getLocation(), Types.VARCHAR)),
				"farmID");
	}

	@Override
	public List<Farm> read()
	{
		return this.executeRead(
				"readFarms",
				Collections.emptyList(),
				new Farm.ResultSetExtractor());
	}

	@Override
	public void update(Farm farm)
	{
		this.executeUpdate(
				"updateFarm",
				Arrays.asList(
						new Parameter("farmID", farm.getId(), Types.INTEGER),
						new Parameter("farmName", farm.getName(), Types.VARCHAR),
						new Parameter("farmLocation", farm.getLocation(), Types.VARCHAR)));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"deleteFarm",
				Collections.singletonList(
						new Parameter("farmID", id, Types.INTEGER)));
	}
}
