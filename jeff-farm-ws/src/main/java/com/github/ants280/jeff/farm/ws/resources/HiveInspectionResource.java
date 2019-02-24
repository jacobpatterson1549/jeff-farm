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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/farm/{farmId}/hive/{hiveId}/hiveInspection")
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
		hiveInspectionDao.create(hiveInspection);

		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getHiveInspection(@PathParam("id") int id)
	{
		HiveInspection hiveInspection = hiveInspectionDao.read(id);

		return Response.ok(hiveInspection).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHiveInspectionsList(@PathParam("hiveId") int hiveId)
	{
		List<HiveInspection> hiveInspections = hiveInspectionDao.readList(hiveId);

		return Response.ok(hiveInspections).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateHiveInspection(HiveInspection hiveInspection)
	{
		hiveInspectionDao.update(hiveInspection);

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
}
