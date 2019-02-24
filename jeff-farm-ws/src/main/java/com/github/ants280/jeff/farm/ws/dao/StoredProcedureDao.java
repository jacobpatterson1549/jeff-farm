package com.github.ants280.jeff.farm.ws.dao;

import java.sql.Types;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnUpdateCount;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class StoredProcedureDao
{
	private final JdbcTemplate jdbcTemplate;

	private static final String STORED_PROCEDURE_NAME = "storedProcedureName";
	private static final String RETURN_UPDATE_COUNT = "UPDATE_COUNT";
	private static final SqlParameter RETURN_UPDATE_COUNT_SQL_PARAMETER
			= new SqlReturnUpdateCount(RETURN_UPDATE_COUNT);

	public StoredProcedureDao(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void executeUpdate(
			String storedProcedureName,
			Collection<Parameter> inParameters)
	{
		Map<String, Object> outputParams = this.execute(
				inParameters,
				storedProcedureName,
				null,
				null);

		assert (int) outputParams.get(RETURN_UPDATE_COUNT) == 1;
	}

	public int executeCreate(
			String storedProcedureName,
			Collection<Parameter> inParameters,
			String outParameterName)
	{
		Map<String, Object> outputParams = this.execute(
				inParameters,
				storedProcedureName,
				outParameterName,
				null);

		assert (int) outputParams.get(RETURN_UPDATE_COUNT) == 1;

		return (int) outputParams.get(outParameterName);
	}

	public <T> List<T> executeRead(
			String storedProcedureName,
			Collection<Parameter> inParameters,
			RowMapper<T> rowMapper)
	{
		Map<String, Object> outputParams = this.execute(
				inParameters,
				storedProcedureName,
				null,
				rowMapper);

		assert (int) outputParams.get(RETURN_UPDATE_COUNT) == 0;

		return (List<T>) outputParams.get(STORED_PROCEDURE_NAME);
	}

	private <T> Map<String, Object> execute(
			Collection<Parameter> inParameters,
			String storedProcedureName,
			String outParameterName,
			RowMapper<T> rowMapper)
	{
		MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValues(inParameters.stream()
						.collect(Collectors.toMap(
								Parameter::getName,
								Parameter::getValue)));
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(storedProcedureName)
				.declareParameters(inParameters.stream()
						.map(parameter -> new SqlParameter(
						parameter.getName(),
						parameter.getSqlType()))
						.toArray(SqlParameter[]::new))
				.declareParameters(RETURN_UPDATE_COUNT_SQL_PARAMETER);

		assert outParameterName == null ^ rowMapper == null;
		if (outParameterName != null)
		{
			simpleJdbcCall.declareParameters(new SqlOutParameter(
					outParameterName,
					Types.INTEGER));
		}
		if (rowMapper != null)
		{
			simpleJdbcCall.returningResultSet(
					STORED_PROCEDURE_NAME,
					rowMapper);
		}

		return simpleJdbcCall.execute(sqlParameterSource);
	}

	public static class Parameter<T>
	{
		private final String name;
		private final T value;
		private final int sqlType;

		public Parameter(String name, T value, int sqlType)
		{
			this.name = name;
			this.value = value;
			this.sqlType = sqlType;
		}

		public String getName()
		{
			return name;
		}

		public T getValue()
		{
			return value;
		}

		public int getSqlType()
		{
			return sqlType;
		}
	}
}
