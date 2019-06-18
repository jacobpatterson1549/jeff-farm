package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.PoultryInspectionGroupDao;
import com.github.ants280.jeff.farm.ws.model.PoultryInspectionGroup;
import com.github.ants280.jeff.farm.ws.model.PoultryInspectionGroupUpdate;
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

@Path("/poultry/inspectionGroup")
public class PoultryInspectionGroupResource
{
	private final PoultryInspectionGroupDao poultryInspectionGroupDao;

	@Inject
	public PoultryInspectionGroupResource(PoultryInspectionGroupDao poultryInspectionGroupDao)
	{
		this.poultryInspectionGroupDao = poultryInspectionGroupDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPoultryInspectionGroup(PoultryInspectionGroup poultryInspectionGroup)
	{
		int id = poultryInspectionGroupDao.create(poultryInspectionGroup);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoultryInspectionGroup(@PathParam("id") int id)
	{
		PoultryInspectionGroup poultryInspectionGroup = poultryInspectionGroupDao.read(id);

		return Response.ok(poultryInspectionGroup).build();
	}

	@GET
	@Path("list/{parentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoultryList(@PathParam("parentId") int parentId)
	{
		List<PoultryInspectionGroup> poultryInspectionGroups
			= poultryInspectionGroupDao.readList(parentId);

		return Response.ok(poultryInspectionGroups).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePoultryInspectionGroup(
		PoultryInspectionGroupUpdate poultryInspectionGroupUpdate)
	{
		poultryInspectionGroupDao.update(poultryInspectionGroupUpdate);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePoultryInspectionGroup(@PathParam("id") int id)
	{
		poultryInspectionGroupDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeletePoultryInspectionGroup(@PathParam("id") int id)
	{
		boolean canDelete = poultryInspectionGroupDao.canDelete(id);

		return Response.ok(canDelete).build();
	}


	@GET
	@Path("targets/parentId")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTargets(@PathParam("parentId") int parentId)
	{
		Map<Integer, String> targets = poultryInspectionGroupDao.getTargets(
			parentId);

		return Response.ok(targets).build();
	}
}
