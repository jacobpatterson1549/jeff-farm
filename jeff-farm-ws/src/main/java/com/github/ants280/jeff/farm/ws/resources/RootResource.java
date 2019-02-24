package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.ApplicationConfig;
import com.github.ants280.jeff.farm.ws.dao.ConnectionDao;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RootResource
{
	private final ConnectionDao connectionDao;

	@Inject
	public RootResource(ConnectionDao connectionDao)
	{
		this.connectionDao = connectionDao;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmsList()
	{
		return Response.ok(this.getVersionInfo()).build();
	}
	
	private Map getVersionInfo()
	{
		Package applicationPackage = ApplicationConfig.class.getPackage();
		boolean hasValidConnection = connectionDao.hasValidConnection();
		
		Map<String, Object> versionInfo = new LinkedHashMap<>();
		versionInfo.put("package", applicationPackage);
		versionInfo.put("hasValidDatabaseConnection", hasValidConnection);
		
		return versionInfo;
	}
}
