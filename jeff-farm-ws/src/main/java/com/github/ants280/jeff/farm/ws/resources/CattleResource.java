package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.CattleDao;
import com.github.ants280.jeff.farm.ws.model.Cattle;
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

@Path("/cattle")
public class CattleResource
{
	private final CattleDao poultryDao;

	@Inject
	public CattleResource(CattleDao poultryDao)
	{
		this.poultryDao = poultryDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCattle(Cattle poultry)
	{
		int id = poultryDao.create(poultry);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCattle(@PathParam("id") int id)
	{
		Cattle poultry = poultryDao.read(id);

		return Response.ok(poultry).build();
	}

	@GET
	@Path("list/{parentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCattleList(@PathParam("parentId") int parentId)
	{
		List<Cattle> poultryList = poultryDao.readList(parentId);

		return Response.ok(poultryList).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCattle(Cattle poultry)
	{
		poultryDao.update(poultry);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCattle(@PathParam("id") int id)
	{
		poultryDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteCattle(@PathParam("id") int id)
	{
		boolean canDelete = poultryDao.canDelete(id);

		return Response.ok(canDelete).build();
	}
}
