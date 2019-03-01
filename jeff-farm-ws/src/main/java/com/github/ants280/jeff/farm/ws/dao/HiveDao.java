package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.Hive;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;
import org.springframework.jdbc.core.RowMapper;

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
						new Parameter(Hive.NAME_COLUMN, hive.getName(), Types.VARCHAR),
						new Parameter(Hive.QUEEN_COLOR_COLUMN, hive.getQueenColorInteger(), Types.BIT)),
				Hive.ID_COLUMN);
	}

	@Override
	public Hive read(int id)
	{
		return this.executeRead(
				"read_hive",
				Collections.singletonList(
						new Parameter(Hive.ID_COLUMN, id, Types.INTEGER)),
				new ResultSetExtractor());
	}

	@Override
	public List<Hive> readList(int parentId)
	{
		return this.executeReadList(
				"read_hives",
				Collections.singletonList(
						new Parameter(Hive.FARM_ID_COLUMN, parentId, Types.INTEGER)),
				new ResultSetExtractor());
	}

	@Override
	public void update(Hive hive)
	{
		this.executeUpdate(
				"update_hive",
				Arrays.asList(
						new Parameter(Hive.ID_COLUMN, hive.getId(), Types.INTEGER),
						new Parameter(Hive.FARM_ID_COLUMN, hive.getFarmId(), Types.INTEGER),
						new Parameter(Hive.NAME_COLUMN, hive.getName(), Types.VARCHAR),
						new Parameter(Hive.QUEEN_COLOR_COLUMN, hive.getQueenColorInteger(), Types.BIT)));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_hive",
				Collections.singletonList(
						new Parameter(Hive.ID_COLUMN, id, Types.INTEGER)));
	}

	private static class ResultSetExtractor implements RowMapper<Hive>
	{
		@Override
		public Hive mapRow(ResultSet rs, int i) throws SQLException
		{
			return new Hive(
					rs.getInt(Hive.ID_COLUMN),
					rs.getInt(Hive.FARM_ID_COLUMN),
					rs.getString(Hive.NAME_COLUMN),
					rs.getInt(Hive.QUEEN_COLOR_COLUMN),
					rs.getTimestamp(Hive.CREATED_DATE_COLUMN),
					rs.getTimestamp(Hive.MODIFIED_DATE_COLUMN));
		}
	}
}
