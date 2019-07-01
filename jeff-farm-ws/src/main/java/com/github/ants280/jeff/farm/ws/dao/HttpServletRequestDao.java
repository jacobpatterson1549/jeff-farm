package com.github.ants280.jeff.farm.ws.dao;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;

@Singleton
public class HttpServletRequestDao
{
	private final HttpServletRequest request;

	@Inject
	public HttpServletRequestDao(HttpServletRequest request)
	{
		this.request = request;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}
}
