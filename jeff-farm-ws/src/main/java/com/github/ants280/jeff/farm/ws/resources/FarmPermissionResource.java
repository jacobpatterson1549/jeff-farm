package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.FarmPermissionDao;
import com.github.ants280.jeff.farm.ws.model.FarmPermission;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/farm/permission")
public class FarmPermissionResource
{
	private final FarmPermissionDao farmPermissionDao;

	@Inject
	public FarmPermissionResource(FarmPermissionDao farmPermissionDao)
	{
		this.farmPermissionDao = farmPermissionDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createFarmPermission(FarmPermission farmPermission)
	{
		int id = farmPermissionDao.create(farmPermission);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmPermission(@PathParam("id") int id)
	{
		FarmPermission farmPermission = farmPermissionDao.read(id);

		return Response.ok(farmPermission).build();
	}

	@GET
	@Path("list/{parentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmPermissionsList(@PathParam("parentId") int parentId)
	{
		List<FarmPermission> farmPermissions = farmPermissionDao.readList(parentId);

		return Response.ok(farmPermissions).build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteFarmPermission(@PathParam("id") int id)
	{
		farmPermissionDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteFarmPermission(@PathParam("id") int id)
	{
		boolean canDelete = farmPermissionDao.canDelete(id);

		return Response.ok(canDelete).build();
	}
}
