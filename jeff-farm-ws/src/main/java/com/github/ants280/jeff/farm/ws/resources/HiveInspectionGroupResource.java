package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.HiveInspectionGroupDao;
import com.github.ants280.jeff.farm.ws.model.HiveInspectionGroup;
import com.github.ants280.jeff.farm.ws.model.HiveInspectionGroupUpdate;
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

@Path("/hive/inspectionGroup")
public class HiveInspectionGroupResource
{
	private final HiveInspectionGroupDao hiveInspectionGroupDao;

	@Inject
	public HiveInspectionGroupResource(HiveInspectionGroupDao hiveInspectionGroupDao)
	{
		this.hiveInspectionGroupDao = hiveInspectionGroupDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createHiveInspectionGroup(HiveInspectionGroup hiveInspectionGroup)
	{
		int id = hiveInspectionGroupDao.create(hiveInspectionGroup);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHiveInspectionGroup(@PathParam("id") int id)
	{
		HiveInspectionGroup hiveInspectionGroup = hiveInspectionGroupDao.read(id);

		return Response.ok(hiveInspectionGroup).build();
	}

	@GET
	@Path("list/{parentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHiveList(@PathParam("parentId") int parentId)
	{
		List<HiveInspectionGroup> hiveInspectionGroups
			= hiveInspectionGroupDao.readList(parentId);

		return Response.ok(hiveInspectionGroups).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateHiveInspectionGroup(
		HiveInspectionGroupUpdate hiveInspectionGroupUpdate)
	{
		hiveInspectionGroupDao.update(hiveInspectionGroupUpdate);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteHiveInspectionGroup(@PathParam("id") int id)
	{
		hiveInspectionGroupDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteHiveInspectionGroup(@PathParam("id") int id)
	{
		boolean canDelete = hiveInspectionGroupDao.canDelete(id);

		return Response.ok(canDelete).build();
	}

	@GET
	@Path("targets/{parentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTargets(@PathParam("parentId") int parentId)
	{
		Map<Integer, String> targets = hiveInspectionGroupDao.getTargets(
			parentId);

		return Response.ok(targets).build();
	}
}
