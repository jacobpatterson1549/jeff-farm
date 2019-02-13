package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.Farm;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@Service
public class FarmDao implements BaseDao<Farm>
{
	private final JdbcTemplate jdbcTemplate;

	@Inject
	public FarmDao(DataSource dataSource)
	{
//		this.jdbcTemplate = null;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(Farm farm)
	{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("farmName", farm.getName())
				.addValue("farmLocation", farm.getLocation());

		return new NamedParameterJdbcTemplate(jdbcTemplate)
				.queryForObject(
						"call createFarm",
						sqlParameterSource,
						Integer.class);
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

		int rowsUpdatedCount = new NamedParameterJdbcTemplate(jdbcTemplate)
				.update(
						"call updateFarm",
						sqlParameterSource);

		assert rowsUpdatedCount == 1;
	}

	@Override
	public void delete(int id)
	{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("farmID", id);

		int rowsUpdatedCount = new NamedParameterJdbcTemplate(jdbcTemplate)
				.update(
						"call deleteFarm",
						sqlParameterSource);

		assert rowsUpdatedCount == 1;
	}
}
