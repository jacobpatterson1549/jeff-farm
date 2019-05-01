package com.github.ants280.jeff.farm.ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class JeffFarmWsExceptionMapper
	implements ExceptionMapper<JeffFarmWsException>
{
	@Override public Response toResponse(JeffFarmWsException exception)
	{
		Logger.getLogger(this.getClass().getName())
			.log(Level.WARNING, "Caught Exception", exception);

		return Response.status(Response.Status.BAD_REQUEST)
			.entity(exception.getMessage())
			.build();
	}
}
