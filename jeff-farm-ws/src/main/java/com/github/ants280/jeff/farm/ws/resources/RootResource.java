package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.ConnectionDao;
import com.github.ants280.jeff.farm.ws.dao.HttpServletRequestDao;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RootResource
{
	private final ConnectionDao connectionDao;
	private final HttpServletRequestDao httpServletRequestDao;

	@Inject
	public RootResource(
		ConnectionDao connectionDao,
		HttpServletRequestDao httpServletRequestDao)
	{
		this.connectionDao = connectionDao;
		this.httpServletRequestDao = httpServletRequestDao;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmsList()
	{
		return Response.ok(this.getVersionInfo()).build();
	}

	private Map getVersionInfo()
	{
		Package applicationPackage = this.getClass().getPackage();
		boolean hasValidConnection = connectionDao.hasValidConnection();
		ServletRequest request = httpServletRequestDao.getRequest();

		Map<String, Object> versionInfo = new LinkedHashMap<>();
		versionInfo.put("package", applicationPackage);
		versionInfo.put("hasValidDatabaseConnection", hasValidConnection);
		versionInfo.put("secureRequest", request.isSecure());
		versionInfo.put(
			"effectiveSessionTrackingModes",
			request.getServletContext().getEffectiveSessionTrackingModes());

		return versionInfo;
	}
}
