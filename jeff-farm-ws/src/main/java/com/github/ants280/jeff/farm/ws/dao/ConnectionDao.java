package com.github.ants280.jeff.farm.ws.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class ConnectionDao
{
	private static final String SQL_SELECT_1 = "";
	private final DataSource dataSource;

	@Inject
	public ConnectionDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public boolean hasValidConnection()
	{
		try (
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement())
		{
			return statement.execute(SQL_SELECT_1);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(this.getClass().getName())
				.log(
					Level.SEVERE,
					"Could not establish valid connection.",
					new SQLException(ex));
			return false;
		}
	}
}
