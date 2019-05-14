package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.PoultryDao;
import com.github.ants280.jeff.farm.ws.model.Poultry;
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

@Path("/farms/{farmId}/poultry")
public class PoultryResource
{
	private final PoultryDao poultryDao;

	@Inject
	public PoultryResource(PoultryDao poultryDao)
	{
		this.poultryDao = poultryDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPoultry(Poultry poultry)
	{
		int id = poultryDao.create(poultry);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoultry(@PathParam("id") int id)
	{
		Poultry poultry = poultryDao.read(id);

		return Response.ok(poultry).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoultryList(@PathParam("farmId") int farmId)
	{
		List<Poultry> poultryList = poultryDao.readList(farmId);

		return Response.ok(poultryList).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePoultry(@PathParam("id") int id, Poultry poultry)
	{
		poultryDao.update(id, poultry);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePoultry(@PathParam("id") int id)
	{
		poultryDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeletePoultry(@PathParam("id") int id)
	{
		boolean canDelete = poultryDao.canDelete(id);

		return Response.ok(canDelete).build();
	}
}
