package com.github.ants280.jeff.farm.ws.dao;

import javax.inject.Inject;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionDao
{
	private static final String SQL_SELECT_1 = "SELECT 1";
	private final JdbcTemplate jdbcTemplate;

	@Inject
	public ConnectionDao(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean hasValidConnection()
	{
		jdbcTemplate.execute(SQL_SELECT_1);
		
		return true;
	}
}
