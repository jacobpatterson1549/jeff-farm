package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.LoginDao;
import com.github.ants280.jeff.farm.ws.dao.UserDao;
import com.github.ants280.jeff.farm.ws.dao.UserIdDao;
import com.github.ants280.jeff.farm.ws.model.User;
import java.util.List;
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
	private final UserIdDao userIdDao;
	private final UserDao userDao;
	private final LoginDao loginDao;

	@Inject
	public UserResource(
		UserIdDao userIdDao, UserDao userDao, LoginDao loginDao)
	{
		this.userIdDao = userIdDao;
		this.userDao = userDao;
		this.loginDao = loginDao;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") int id)
	{
		User user = userDao.read(id);

		return Response.ok(user).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, User user)
	{
		userDao.update(id, user);

		return Response.ok().build();
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFarmsList()
	{
		List<User> users = userDao.readList(-1); // (user_id provided by SqlFunctionCall)

		return Response.ok(users).build();
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
	public Response logout()
	{
		loginDao.logout();

		return Response.ok().build();
	}
}
