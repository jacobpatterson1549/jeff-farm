package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.Farm;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnUpdateCount;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Service
public class FarmDao implements BaseDao<Farm>
{
	private final JdbcTemplate jdbcTemplate;
	// TODO: Move this to BaseDao (and make abstract)
	private static final String RETURN_UPDATE_COUNT = "UPDATE_COUNT";
	private static final SqlParameter RETURN_UPDATE_COUNT_SQL_PARAMETER
			= new SqlReturnUpdateCount(RETURN_UPDATE_COUNT);

	@Inject
	public FarmDao(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(Farm farm)
	{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("farmName", farm.getName())
				.addValue("farmLocation", farm.getLocation());

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("createFarm")
				.declareParameters(new SqlParameter("farmName", Types.VARCHAR))
				.declareParameters(new SqlParameter("farmLocation", Types.VARCHAR))
				.declareParameters(new SqlOutParameter("farmID", Types.INTEGER));

		Map<String, Object> outputParams = simpleJdbcCall.execute(sqlParameterSource);
		assert (int) outputParams.get(RETURN_UPDATE_COUNT) == 1;
		return (int) outputParams.get("farmID");
	}

	@Override
	public List<Farm> read()
	{
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("readFarms")
				.returningResultSet("resultSet", new Farm.ResultSetExtractor())
				.declareParameters(RETURN_UPDATE_COUNT_SQL_PARAMETER);

		Map<String, Object> outputParams = simpleJdbcCall.execute();
		assert (int) outputParams.get(RETURN_UPDATE_COUNT) == 0;
		return (List<Farm>) outputParams.get("resultSet");
	}

	@Override
	public void update(Farm farm)
	{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("farmID", farm.getId())
				.addValue("farmName", farm.getName())
				.addValue("farmLocation", farm.getLocation());

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("updateFarm")
				.declareParameters(new SqlParameter("farmID", Types.INTEGER))
				.declareParameters(new SqlParameter("farmName", Types.VARCHAR))
				.declareParameters(new SqlParameter("farmLocation", Types.VARCHAR))
				.declareParameters(RETURN_UPDATE_COUNT_SQL_PARAMETER);

		Map<String, Object> outputParams
				= simpleJdbcCall.execute(sqlParameterSource);
		assert (int) outputParams.get(RETURN_UPDATE_COUNT) == 1;
	}

	@Override
	public void delete(int id)
	{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("farmID", id);

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("deleteFarm")
				.declareParameters(new SqlParameter("farmID", Types.INTEGER))
				.declareParameters(RETURN_UPDATE_COUNT_SQL_PARAMETER);

		Map<String, Object> outputParams = simpleJdbcCall.execute(sqlParameterSource);
		assert (int) outputParams.get(RETURN_UPDATE_COUNT) == 1;
	}
}
