package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.HiveInspectionDao;
import com.github.ants280.jeff.farm.ws.model.HiveInspection;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hive/inspection")
public class HiveInspectionResource
{
	private final HiveInspectionDao hiveInspectionDao;

	@Inject
	public HiveInspectionResource(HiveInspectionDao hiveInspectionDao)
	{
		this.hiveInspectionDao = hiveInspectionDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createHiveInspection(HiveInspection hiveInspection)
	{
		int id = hiveInspectionDao.create(hiveInspection);

		return Response.ok(id).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHiveInspection(@PathParam("id") int id)
	{
		HiveInspection hiveInspection = hiveInspectionDao.read(id);

		return Response.ok(hiveInspection).build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHiveInspectionsList(@QueryParam("hiveId") int hiveId)
	{
		List<HiveInspection> hiveInspections
			= hiveInspectionDao.readList(hiveId);

		return Response.ok(hiveInspections).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateHiveInspection(
		@PathParam("id") int id,
		HiveInspection hiveInspection)
	{
		hiveInspectionDao.update(id, hiveInspection);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteHiveInspection(@PathParam("id") int id)
	{
		hiveInspectionDao.delete(id);

		return Response.ok().build();
	}

	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteHiveInspection(@PathParam("id") int id)
	{
		boolean canDelete = hiveInspectionDao.canDelete(id);

		return Response.ok(canDelete).build();
	}
}
