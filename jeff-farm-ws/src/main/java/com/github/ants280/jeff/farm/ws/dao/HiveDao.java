package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.Hive;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

@Singleton
public class HiveDao extends StoredProcedureDao implements CrudDao<Hive>
{
	private final LoginDao loginDao;

	@Inject
	public HiveDao(DataSource dataSource, LoginDao loginDao)
	{
		super(dataSource);
		this.loginDao = loginDao;
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
				Hive.ID_COLUMN,
				loginDao.getUserId());
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
						new Parameter(Hive.QUEEN_COLOR_COLUMN, hive.getQueenColorInteger(), Types.BIT)),
				loginDao.getUserId());
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_hive",
				Collections.singletonList(
						new Parameter(Hive.ID_COLUMN, id, Types.INTEGER)),
				loginDao.getUserId());
	}
	
	@Override
	public boolean canDelete(int id)
	{
		return this.executeReadBoolean("can_delete_hive",
				Collections.singletonList(
						new Parameter(Hive.ID_COLUMN, id, Types.INTEGER)),
				Hive.CAN_DELETE_ITEM);
	}

	private static class ResultSetExtractor implements RowMapper<Hive>
	{
		@Override
		public Hive mapRow(ResultSet rs, int i) throws SQLException
		{
			return new Hive()
					.setId(rs.getInt(Hive.ID_COLUMN))
					.setFarmId(rs.getInt(Hive.FARM_ID_COLUMN))
					.setName(rs.getString(Hive.NAME_COLUMN))
					.setQueenColorInteger(rs.getInt(Hive.QUEEN_COLOR_COLUMN))
					.setCreatedDate(rs.getTimestamp(Hive.CREATED_DATE_COLUMN))
					.setModifiedDate(rs.getTimestamp(Hive.MODIFIED_DATE_COLUMN));
		}
	}
}
