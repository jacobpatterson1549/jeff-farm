package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.Property;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;

@Singleton
public class UserIdDao
{
	private static final String USER_ID_SESSION_ATTRIBUTE = "userId";
	private final HttpServletRequestDao httpServletRequestDao;

	@Inject
	public UserIdDao(HttpServletRequestDao httpServletRequestDao)
	{
		this.httpServletRequestDao = httpServletRequestDao;
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
		return httpServletRequestDao.getRequest()
			.isUserInRole(Property.USER_ROLE.getValue());
	}

	public boolean hasAdimnRole()
	{
		return httpServletRequestDao.getRequest()
			.isUserInRole(Property.ADMIN_ROLE.getValue());
	}


	public boolean hasDemoRole()
	{
		return httpServletRequestDao.getRequest()
			.isUserInRole(Property.DEMO_ROLE.getValue());
	}

	private HttpSession getCurrentSession()
	{
		return httpServletRequestDao.getRequest()
			.getSession(false);
	}
}
