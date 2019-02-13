package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.Farm;
import java.sql.Types;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Service
public class FarmDao implements BaseDao<Farm>
{
	private final JdbcTemplate jdbcTemplate;

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
		
		return (int) simpleJdbcCall.execute(sqlParameterSource)
				.get("farmID");
	}

	@Override
	public List<Farm> read()
	{
		return jdbcTemplate.query(
				"call readFarms",
				new Farm.ResultSetExtractor());
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
				.declareParameters(new SqlParameter("farmLocation", Types.VARCHAR));
		
		simpleJdbcCall.execute(sqlParameterSource);
	}

	@Override
	public void delete(int id)
	{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("farmID", id);

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("deleteFarm")
				.declareParameters(new SqlParameter("farmID", Types.INTEGER));
		
		simpleJdbcCall.execute(sqlParameterSource);
	}
}
