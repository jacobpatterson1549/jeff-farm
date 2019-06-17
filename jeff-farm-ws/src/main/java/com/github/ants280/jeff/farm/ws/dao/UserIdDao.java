package com.github.ants280.jeff.farm.ws.dao;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
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
		return (int) request.getSession(false)
			.getAttribute(USER_ID_SESSION_ATTRIBUTE);
	}

	public void setUserId(int userId)
	{
		request.getSession(false)
			.setAttribute(USER_ID_SESSION_ATTRIBUTE, userId);
	}
}
