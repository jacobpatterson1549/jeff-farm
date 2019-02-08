package com.github.ants280.jeff.farm.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("hello")
public class HelloResource
{
	@GET
	public String hello(@QueryParam("name") String name)
	{
		// http://localhost:8080/jeff-farm-ws/resources/hello?name=jacob
		return String.format("Hello, %s!", name);
	}

//	@Resource(lookup = "jdbc/jeff-farm")
//	private DataSource ds;
}
