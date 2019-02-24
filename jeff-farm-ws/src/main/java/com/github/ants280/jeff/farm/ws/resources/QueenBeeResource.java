package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.QueenBeeDao;
import com.github.ants280.jeff.farm.ws.model.QueenBee;
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

@Path("/farm/{farmId}/hive/{hiveId}/queenBee")
public class QueenBeeResource
{
	private final QueenBeeDao queenBeeDao;

	@Inject
	public QueenBeeResource(QueenBeeDao queenBeeDao)
	{
		this.queenBeeDao = queenBeeDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createQueenBee(QueenBee queenBee)
	{
		queenBeeDao.create(queenBee);

		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getQueenBee(@PathParam("id") int id)
	{
		QueenBee queenBee = queenBeeDao.read(id);

		return Response.ok(queenBee).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQueenBeesList(@PathParam("hiveId") int hiveId)
	{
		List<QueenBee> queenBees = queenBeeDao.readList(hiveId);

		return Response.ok(queenBees).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQueenBee(QueenBee queenBee)
	{
		queenBeeDao.update(queenBee);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteQueenBee(@PathParam("id") int id)
	{
		queenBeeDao.delete(id);

		return Response.ok().build();
	}
}
