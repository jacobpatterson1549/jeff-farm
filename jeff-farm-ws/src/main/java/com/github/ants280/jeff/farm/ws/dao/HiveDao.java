package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.StoredProcedureDao.Parameter;
import com.github.ants280.jeff.farm.ws.model.Hive;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;

@Service
public class HiveDao extends StoredProcedureDao implements CrudDao<Hive>
{
	@Inject
	public HiveDao(DataSource dataSource)
	{
		super(dataSource);
	}

	@Override
	public int create(Hive hive)
	{
		return this.executeCreate(
				"create_hive",
				Arrays.asList(
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER),
						new Parameter(Hive.NAME_COLUMN, hive.getName(), Types.VARCHAR)),
				Hive.ID_COLUMN);
	}

	@Override
	public Hive read(int id)
	{
		return this.executeRead(
				"read_hive",
				Collections.singletonList(
						new Parameter(Hive.ID_COLUMN, id, Types.INTEGER)),
				new Hive.ResultSetExtractor());
	}
	
	@Override
	public List<Hive> readList(int parentId)
	{
		return this.executeReadList(
				"read_hives",
				Collections.singletonList(
						new Parameter(Hive.FARM_ID_COLUMN, parentId, Types.INTEGER)),
				new Hive.ResultSetExtractor());
	}

	@Override
	public void update(Hive hive)
	{
		this.executeUpdate(
				"update_hive",
				Arrays.asList(
						new Parameter(Hive.ID_COLUMN, hive.getId(), Types.INTEGER),
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER),
						new Parameter(Hive.NAME_COLUMN, hive.getName(), Types.VARCHAR)));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_hive",
				Collections.singletonList(
						new Parameter(Hive.ID_COLUMN, id, Types.INTEGER)));
	}
}
