package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.Property;
import com.github.ants280.jeff.farm.ws.model.User;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Singleton
public class LoginDao
{
	private final UserDao userDao;
	private final UserIdDao userIdDao;
	private final HttpServletRequest request;

	@Inject
	public LoginDao(
		UserDao userDao, UserIdDao userIdDao, HttpServletRequest request)
	{
		this.userDao = userDao;
		this.userIdDao = userIdDao;
		this.request = request;
	}

	public void login(User user) throws ServletException
	{
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null)
		{
			if (request.getUserPrincipal() != null
				&& request.getRemoteUser() != null
				&& request.getRemoteUser().equals(user.getUserName()))
			{
				return;
			}
			oldSession.invalidate();
		}
		
		// The session MUST be crated before the login call.
		// See org.apache.catalina.authenticator.AuthenticatorBase.register()
		HttpSession session = request.getSession(true);

		request.login(user.getUserName(), user.getPassword());

		User actualUser = userDao.read(user.getUserName());
		userIdDao.setUserId(actualUser.getId());

		if (!request.isUserInRole(Property.USER_ROLE.getValue()))
		{
			throw new JeffFarmWsException("No access");
		}
	}

	public void logout()
	{
		HttpSession session = request.getSession(false);

		if (session != null)
		{
			session.invalidate();
		}
	}
}
