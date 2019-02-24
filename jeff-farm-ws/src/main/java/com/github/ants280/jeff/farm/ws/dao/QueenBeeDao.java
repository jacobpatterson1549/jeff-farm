package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.StoredProcedureDao.Parameter;
import com.github.ants280.jeff.farm.ws.model.Hive;
import com.github.ants280.jeff.farm.ws.model.QueenBee;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;

@Service
public class QueenBeeDao extends StoredProcedureDao implements CrudDao<QueenBee>
{
	private final HiveDao hiveDao; // TODO: should not be needed.

	@Inject
	public QueenBeeDao(DataSource dataSource, HiveDao hiveDao)
	{
		super(dataSource);
		this.hiveDao = hiveDao;
	}

	@Override
	public int create(QueenBee queenBee)
	{
		List<Hive> hives = hiveDao.read();
		Hive hive = hives.stream()
				.filter(hive2 -> hive2.getId() == queenBee.getHiveId())
				.findFirst()
				.orElseThrow(() -> new AssertionError("No Hive found for id #" + queenBee.getHiveId()));

		return this.executeCreate(
				"create_queen_bee",
				Arrays.asList(
						new Parameter(QueenBee.HIVE_ID_COLUMN, queenBee.getHiveId(), Types.INTEGER),
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER),
						new Parameter(QueenBee.MARK_COLOR_COLUMN, queenBee.getMarkColor(), Types.VARCHAR)),
				QueenBee.ID_COLUMN);
	}

	@Override
	public List<QueenBee> read()
	{
		return this.executeRead(
				"read_queen_bees",
				Collections.emptyList(),
				new QueenBee.ResultSetExtractor());
	}

	@Override
	public void update(QueenBee queenBee)
	{
		List<Hive> hives = hiveDao.read();
		Hive hive = hives.stream()
				.filter(hive2 -> hive2.getId() == queenBee.getHiveId())
				.findFirst()
				.orElseThrow(() -> new AssertionError("No Hive found for id #" + queenBee.getHiveId()));

		this.executeUpdate(
				"update_queen_bee",
				Arrays.asList(
						new Parameter(QueenBee.ID_COLUMN, queenBee.getId(), Types.INTEGER),
						new Parameter(QueenBee.HIVE_ID_COLUMN, queenBee.getHiveId(), Types.INTEGER),
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER),
						new Parameter(QueenBee.MARK_COLOR_COLUMN, queenBee.getMarkColor(), Types.VARCHAR)));
	}

	@Override
	public void delete(int id)
	{
		List<QueenBee> queenBees = this.read();
		QueenBee queenBee = queenBees.stream()
				.filter(queenBee2 -> queenBee2.getId() == id)
				.findFirst()
				.orElseThrow(() -> new AssertionError("No QueenBee found for queenBee #" + id));
		List<Hive> hives = hiveDao.read();
		Hive hive = hives.stream()
				.filter(hive2 -> hive2.getId() == queenBee.getHiveId())
				.findFirst()
				.orElseThrow(() -> new AssertionError("No Hive found for id #" + queenBee.getHiveId()));

		this.executeUpdate(
				"delete_queen_bee",
				Arrays.asList(
						new Parameter(QueenBee.ID_COLUMN, id, Types.INTEGER),
						new Parameter(QueenBee.HIVE_ID_COLUMN, queenBee.getHiveId(), Types.INTEGER),
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER)));
	}
}
