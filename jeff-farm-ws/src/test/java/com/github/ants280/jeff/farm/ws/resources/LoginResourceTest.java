package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.LoginDao;
import com.github.ants280.jeff.farm.ws.model.User;
import javax.servlet.ServletException;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginResourceTest
{
	@Test
	public void testLoginUser_returnsSessionIdAsJson() throws ServletException
	{
		String sessionId = "sessionId";
		LoginDao mockSessionDao = Mockito.mock(LoginDao.class);
		User mockUser = Mockito.mock(User.class);
		Mockito.when(mockSessionDao.login(mockUser)).thenReturn(sessionId);
		LoginResource loginResource = new LoginResource(mockSessionDao, null);
		
		Response response = loginResource.login(mockUser);
		
		Object entity = response.getEntity();
		String expectedEntity = "\"sessionId\"";
		Assert.assertEquals(expectedEntity, entity);
	}
}
