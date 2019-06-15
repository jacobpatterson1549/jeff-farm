package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.LoginDao;
import com.github.ants280.jeff.farm.ws.dao.UserDao;
import com.github.ants280.jeff.farm.ws.dao.UserIdDao;
import com.github.ants280.jeff.farm.ws.model.User;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser()
	{
		int id = userIdDao.getUserId();
		User user = userDao.read(id);

		return Response.ok(user).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(User user)
	{
		userDao.update(user.getId(), user);

		return Response.ok().build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser()
	{
		int id = userIdDao.getUserId();
		userDao.delete(id);

		return Response.ok().build();
	}


	@GET
	@Path("canDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response canDeleteUser()
	{
		int id = userIdDao.getUserId();
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
