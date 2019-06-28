package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.LivestockDao;
import com.github.ants280.jeff.farm.ws.model.Livestock;
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

@Path("/livestock")
public class LivestockResource
{
	private final LivestockDao livestockDao;

	@Inject
	public LivestockResource(LivestockDao livestockDao)
	{
		this.livestockDao = livestockDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLivestock(Livestock poultry)
	{
		int id = livestockDao.create(poultry);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLivestock(@PathParam("id") int id)
	{
		Livestock poultry = livestockDao.read(id);

		return Response.ok(poultry).build();
	}

	@GET
	@Path("list/{parentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLivestockList(@PathParam("parentId") int parentId)
	{
		List<Livestock> poultryList = livestockDao.readList(parentId);

		return Response.ok(poultryList).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateLivestock(Livestock poultry)
	{
		livestockDao.update(poultry);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteLivestock(@PathParam("id") int id)
	{
		livestockDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteLivestock(@PathParam("id") int id)
	{
		boolean canDelete = livestockDao.canDelete(id);

		return Response.ok(canDelete).build();
	}
}
