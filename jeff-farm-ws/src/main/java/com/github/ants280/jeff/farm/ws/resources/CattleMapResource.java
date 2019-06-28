package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.CattleMapDao;
import com.github.ants280.jeff.farm.ws.model.CrudItemMap;
import com.github.ants280.jeff.farm.ws.model.CrudItemMapUpdate;
import java.util.List;
import java.util.Map;
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

@Path("/cattle/map")
public class CattleMapResource
{
	private final CattleMapDao cattleMapDao;

	@Inject
	public CattleMapResource(CattleMapDao cattleMapDao)
	{
		this.cattleMapDao = cattleMapDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCattleMap(CrudItemMap cattleMap)
	{
		int id = cattleMapDao.create(cattleMap);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCattleMap(@PathParam("id") int id)
	{
		CrudItemMap cattleMap = cattleMapDao.read(id);

		return Response.ok(cattleMap).build();
	}

	@GET
	@Path("list/{parentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoultryList(@PathParam("parentId") int parentId)
	{
		List<CrudItemMap> cattleMaps
			= cattleMapDao.readList(parentId);

		return Response.ok(cattleMaps).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCattleMap(CrudItemMapUpdate cattleMapUpdate)
	{
		cattleMapDao.update(cattleMapUpdate);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCattleMap(@PathParam("id") int id)
	{
		cattleMapDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteCattleMap(@PathParam("id") int id)
	{
		return Response.ok(true).build();
	}

	@GET
	@Path("targets/{parentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTargets(@PathParam("parentId") int parentId)
	{
		Map<Integer, String> targets = cattleMapDao.getTargets(
			parentId);

		return Response.ok(targets).build();
	}
}
