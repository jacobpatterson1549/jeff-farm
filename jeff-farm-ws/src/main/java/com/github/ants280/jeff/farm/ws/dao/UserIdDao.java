package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.Property;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@Singleton
public class UserIdDao
{
	private static final String USER_ID_SESSION_ATTRIBUTE = "userId";
	private final HttpServletRequest request;

	@Inject
	public UserIdDao(@Context HttpServletRequest request)
	{
		this.request = request;
	}

	public int getUserId()
	{
		return (int) this.getCurrentSession()
			.getAttribute(USER_ID_SESSION_ATTRIBUTE);
	}

	public void setUserId(int userId)
	{
		this.getCurrentSession()
			.setAttribute(USER_ID_SESSION_ATTRIBUTE, userId);
	}

	public boolean hasUserRole()
	{
		return request.isUserInRole(Property.USER_ROLE.getValue());
	}

	public boolean hasAdimnRole()
	{
		return request.isUserInRole(Property.ADMIN_ROLE.getValue());
	}


	public boolean hasDemoRole()
	{
		return request.isUserInRole(Property.DEMO_ROLE.getValue());
	}

	private HttpSession getCurrentSession()
	{
		return request.getSession(false);
	}
}
