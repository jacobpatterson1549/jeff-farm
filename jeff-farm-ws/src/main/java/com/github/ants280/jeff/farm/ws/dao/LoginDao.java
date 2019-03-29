package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.User;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

public class LoginDao
{
	private static final String USER_ID_SESSION_ATTRIBUTE = "userId";
	private final UserDao userDao;
	@Context
	private HttpServletRequest httpServletRequest;

	@Inject
	public LoginDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	public String login(User user) throws ServletException
	{
		httpServletRequest.login(user.getUserName(), user.getPassword());
		
		User actualUser = userDao.read(user.getUserName());

		HttpSession session = httpServletRequest.getSession();
		
		session.setAttribute(USER_ID_SESSION_ATTRIBUTE, actualUser.getId());
		
		return session.getId();
	}

	public void logout()
	{
		HttpSession session = httpServletRequest.getSession(false);
		
		if (session == null)
		{
			throw new IllegalArgumentException("No Session");
		}
		
		session.invalidate();
	}
	
	public int getUserId()
	{
		HttpSession session = httpServletRequest.getSession(false);
		
		if (session == null)
		{
			throw new IllegalArgumentException("No Session");
		}
		
		return (int) session
				.getAttribute(USER_ID_SESSION_ATTRIBUTE);
	}
}
