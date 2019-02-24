package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.StoredProcedureDao.Parameter;
import com.github.ants280.jeff.farm.ws.model.QueenBee;
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
public class QueenBeeDao extends StoredProcedureDao implements CrudDao<QueenBee>
{
	@Inject
	public QueenBeeDao(DataSource dataSource)
	{
		super(dataSource);
	}

	@Override
	public int create(QueenBee queenBee)
	{
		return this.executeCreate(
				"create_queen_bee",
				Arrays.asList(
						new Parameter(QueenBee.HIVE_ID_COLUMN, queenBee.getHiveId(), Types.INTEGER),
						new Parameter(QueenBee.MARK_COLOR_COLUMN, queenBee.getMarkColor(), Types.VARCHAR)),
				QueenBee.ID_COLUMN);
	}
	
	@Override
	public QueenBee read(int id)
	{
		return this.executeRead(
				"read_hive_inspection",
				Collections.singletonList(
						new Parameter(QueenBee.ID_COLUMN, id, Types.INTEGER)),
				new ResultSetExtractor());
	}

	@Override
	public List<QueenBee> readList(int parentId)
	{
		return this.executeReadList(
				"read_queen_bees",
				Collections.singletonList(
						new Parameter(QueenBee.HIVE_ID_COLUMN, parentId, Types.INTEGER)),
				new ResultSetExtractor());
	}

	@Override
	public void update(QueenBee queenBee)
	{
		this.executeUpdate(
				"update_queen_bee",
				Arrays.asList(
						new Parameter(QueenBee.ID_COLUMN, queenBee.getId(), Types.INTEGER),
						new Parameter(QueenBee.HIVE_ID_COLUMN, queenBee.getHiveId(), Types.INTEGER),
						new Parameter(QueenBee.MARK_COLOR_COLUMN, queenBee.getMarkColor(), Types.VARCHAR)));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_queen_bee",
				Collections.singletonList(
						new Parameter(QueenBee.ID_COLUMN, id, Types.INTEGER)));
	}
	
	private static class ResultSetExtractor implements RowMapper<QueenBee>
	{
		@Override
		public QueenBee mapRow(ResultSet rs, int i) throws SQLException
		{
			QueenBee queenBee = new QueenBee();
			queenBee.setId(rs.getInt(QueenBee.ID_COLUMN));
			queenBee.setHiveId(rs.getInt(QueenBee.HIVE_ID_COLUMN));
			queenBee.setMarkColor(rs.getString(QueenBee.MARK_COLOR_COLUMN));
			return queenBee;
		}
	}
}
