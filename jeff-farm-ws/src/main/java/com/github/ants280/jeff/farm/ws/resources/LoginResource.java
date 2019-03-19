package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.UserDao;
import com.github.ants280.jeff.farm.ws.model.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource
{
	private final UserDao userDao;

	@Inject
	public LoginResource(UserDao userDao)
	{
		this.userDao = userDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginUser(User user)
	{
		try
		{
			String sessionId = userDao.login(user);
			sessionId = String.format("\"%s\"", sessionId); // TODO: hack to convert string to json
			
			return Response.ok(sessionId).build();
		}
		catch (ServletException ex)
		{
			Logger.getLogger(LoginResource.class.getName())
					.log(
							Level.SEVERE,
							String.format(
									"Could not log in as username = '%s'",
									user == null ? "" : user.getUserName()),
							ex);
			
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(ex.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("create")
	public Response createUser(User user)
	{
		int id = userDao.create(user);

		return Response.ok(id).build();
	}
}
