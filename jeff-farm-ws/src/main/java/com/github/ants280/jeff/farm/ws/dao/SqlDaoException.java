package com.github.ants280.jeff.farm.ws.dao;

import java.sql.SQLException;
import java.sql.SQLWarning;

/**
 * Formatting of SQLException and SQLWarning from
 * https://docs.oracle.com/javase/tutorial/jdbc/basics/sqlexception.html
 */
public class SqlDaoException extends RuntimeException
{
	public SqlDaoException(String message)
	{
		super(message);
	}

	public SqlDaoException(SQLException cause)
	{
		super(getMessage(cause), cause);
	}

	public SqlDaoException(SQLWarning warning)
	{
		super(getMessage(warning));
	}

	private static String getMessage(SQLException ex)
	{
		return String.format(
			"SQLState: %s%nError Code: %d%nMessage: %s",
			ex.getSQLState(),
			ex.getErrorCode(),
			ex.getMessage());
	}

	private static String getMessage(SQLWarning warning)
	{
		StringBuilder warningsMessage = new StringBuilder("---Warning---");

		do
		{
			warningsMessage.append("%nMessage: ")
				.append(warning.getMessage())
				.append("%nSQLState: ")
				.append(warning.getSQLState())
				.append("%nVendor error code: ")
				.append(warning.getErrorCode());

			warning = warning.getNextWarning();
		}
		while (warning != null);

		return warningsMessage.toString();
	}
}
