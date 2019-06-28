package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.LivestockMapDao;
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

@Path("/livestock/map")
public class LivestockMapResource
{
	private final LivestockMapDao livestockMapDao;

	@Inject
	public LivestockMapResource(LivestockMapDao livestockMapDao)
	{
		this.livestockMapDao = livestockMapDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLivestockMap(CrudItemMap livestockMap)
	{
		int id = livestockMapDao.create(livestockMap);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLivestockMap(@PathParam("id") int id)
	{
		CrudItemMap livestockMap = livestockMapDao.read(id);

		return Response.ok(livestockMap).build();
	}

	@GET
	@Path("list/{parentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoultryList(@PathParam("parentId") int parentId)
	{
		List<CrudItemMap> livestockMaps
			= livestockMapDao.readList(parentId);

		return Response.ok(livestockMaps).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateLivestockMap(CrudItemMapUpdate livestockMapUpdate)
	{
		livestockMapDao.update(livestockMapUpdate);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteLivestockMap(@PathParam("id") int id)
	{
		livestockMapDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteLivestockMap(@PathParam("id") int id)
	{
		return Response.ok(true).build();
	}

	@GET
	@Path("targets/{parentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTargets(@PathParam("parentId") int parentId)
	{
		Map<Integer, String> targets = livestockMapDao.getTargets(
			parentId);

		return Response.ok(targets).build();
	}
}
