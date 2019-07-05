package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.Property;
import com.github.ants280.jeff.farm.ws.dao.api.SqlDaoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class ConnectionDao
{
	private final DataSource dataSource;

	@Inject
	public ConnectionDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public boolean hasValidConnection()
	{
		try (
			Connection connection = dataSource.getConnection())
		{
			String validationQueryTimeoutSeconds
				= Property.VALIDATION_QUERY_TIMEOUT_SECONDS.getValue();
			int timeout = Integer.parseInt(validationQueryTimeoutSeconds);
			return connection.isValid(timeout);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(this.getClass().getName())
				.log(
					Level.SEVERE,
					"Could not establish valid connection.",
					new SqlDaoException(ex));
			return false;
		}
	}
}
