package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.FarmDao;
import com.github.ants280.jeff.farm.ws.model.Farm;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// http://localhost:8080/jeff-farm-ws/farm
@Path("farm")
public class FarmResource
{
//	@Resource(lookup = "jeff-farm-data-source")
	@Resource(lookup = "jdbc/jeff-farm-data-source")
//	@Resource(lookup = "java:comp/env/jdbc/jeff-farm-data-source")
	private DataSource dataSource;

	private final FarmDao farmDao;

	@Inject
	public FarmResource(FarmDao farmDao)
	{
		this.farmDao = farmDao;
	}

	@GET
	@Path("hello")
	public String hello(@QueryParam("name") String name) throws Exception
	{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jeff-farm-data-source");
		DataSource ds2 = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/jeff-farm-data-source");
		System.out.println(dataSource);
		return String.format("Hello, %s!", name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createFarm(Farm farm)
	{
		farmDao.create(farm);

		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarms()
	{
		List<Farm> farms = farmDao.read();

		return Response.ok(farms).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateFarm(Farm farm)
	{
		farmDao.update(farm);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteFarm(@PathParam("id") int farmId)
	{
		farmDao.delete(farmId);

		return Response.ok().build();
	}
}
