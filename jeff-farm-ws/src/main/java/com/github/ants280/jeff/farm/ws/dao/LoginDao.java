package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
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
	private final HttpServletRequestDao httpServletRequestDao;

	@Inject
	public LoginDao(
		UserDao userDao,
		UserIdDao userIdDao,
		HttpServletRequestDao httpServletRequestDao)
	{
		this.userDao = userDao;
		this.userIdDao = userIdDao;
		this.httpServletRequestDao = httpServletRequestDao;
	}

	public String login(User user) throws ServletException
	{
		HttpServletRequest request = httpServletRequestDao.getRequest();
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null)
		{
			if (request.getUserPrincipal() != null
				&& request.getRemoteUser() != null
				&& request.getRemoteUser().equals(user.getUserName()))
			{
				return oldSession.getId();
			}
			oldSession.invalidate();
		}
		
		// The session MUST be crated before the login call.
		// See org.apache.catalina.authenticator.AuthenticatorBase.register()
		HttpSession session = request.getSession(true);

		request.login(user.getUserName(), user.getPassword());

		User actualUser = userDao.read(user.getUserName());
		userIdDao.setUserId(actualUser.getId());

		if (!userIdDao.hasUserRole() && !userIdDao.hasDemoRole())
		{
			throw new JeffFarmWsException("No access");
		}

		// Send the session id.  Sent in url.
		// Retrieved at CoyoteAdapter.postParseRequest::SessionTrackingMode.URL
		return session.getId();
	}

	public void logout()
	{
		HttpServletRequest request = httpServletRequestDao.getRequest();
		HttpSession session = request.getSession(false);

		if (session != null)
		{
			session.invalidate();
		}
	}
}
