package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.UserDao;
import com.github.ants280.jeff.farm.ws.model.User;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource
{
	private final UserDao userDao;

	@Inject
	public UserResource(UserDao userDao)
	{
		this.userDao = userDao;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}") // TODO: Should be able to get user without id.  Same for other paths of {id} in this resource.
	public Response getUser(@PathParam("id") int id)
	{
		User user = userDao.read(id);

		return Response.ok(user).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(User user)
	{
		userDao.update(user);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id)
	{
		userDao.delete(id);

		return Response.ok().build();
	}
	
	@GET
	@Path("{id}/canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteUser(@PathParam("id") int id)
	{
		boolean canDelete = userDao.canDelete(id);

		return Response.ok(canDelete).build();
	}
		
	@GET
	@Path("logout")
	public Response canDeleteUser()
	{
		userDao.logout();
		return Response.ok().build();
	}
}
