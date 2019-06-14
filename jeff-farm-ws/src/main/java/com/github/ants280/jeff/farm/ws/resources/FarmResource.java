package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.FarmDao;
import com.github.ants280.jeff.farm.ws.model.Farm;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/farms")
public class FarmResource
{
	private final FarmDao farmDao;

	@Inject
	public FarmResource(FarmDao farmDao)
	{
		this.farmDao = farmDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createFarm(Farm farm)
	{
		int id = farmDao.create(farm);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarm(@PathParam("id") int id)
	{
		Farm farm = farmDao.read(id);

		return Response.ok(farm).build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmsList()
	{
		List<Farm> farms = farmDao.readList(-1); // TODO limit this by permissions

		return Response.ok(farms).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateFarm(@PathParam("id") int id, Farm farm)
	{
		farmDao.update(id, farm);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteFarm(@PathParam("id") int id)
	{
		farmDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteFarm(@PathParam("id") int id)
	{
		boolean canDelete = farmDao.canDelete(id);

		return Response.ok(canDelete).build();
	}
}
