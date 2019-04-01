package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.User;
import java.util.logging.Level;
import java.util.logging.Logger;
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
	private HttpServletRequest request;

	@Inject
	public LoginDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	public void login(User user) throws ServletException
	{
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null)
		{
			oldSession.invalidate();
		}

		request.login(user.getUserName(), user.getPassword());

		User actualUser = userDao.read(user.getUserName());
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute(USER_ID_SESSION_ATTRIBUTE, actualUser.getId());
		
		Logger logger = Logger.getLogger(this.getClass().getName());
		logger.log(Level.INFO, "New sessionId is {0}", session.getId());
	}

	public void logout()
	{
		HttpSession session = request.getSession(false);
		
		if (session != null)
		{
			session.invalidate();
		}
	}
	
	public int getUserId()
	{
		HttpSession session = request.getSession(false);
		
		if (session == null)
		{
			throw new IllegalArgumentException("No Session");
		}
		
		return (int) session
				.getAttribute(USER_ID_SESSION_ATTRIBUTE);
	}
}
