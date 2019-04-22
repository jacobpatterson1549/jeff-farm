package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.LoginDao;
import com.github.ants280.jeff.farm.ws.dao.UserDao;
import com.github.ants280.jeff.farm.ws.model.User;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginResourceTest extends JerseyTest
{
	private LoginDao mockLoginDao;
	private UserDao mockUserDao;

	@Override
	protected Application configure()
	{
		mockLoginDao = mock(LoginDao.class);
		mockUserDao = mock(UserDao.class);
		return new ResourceConfig().register(LoginResource.class)
				.register(new AbstractBinder()
				{
					@Override
					protected void configure()
					{
						bind(mockLoginDao).to(LoginDao.class);
						bind(mockUserDao).to(UserDao.class);
					}
				});
	}

	@Test
	public void testLogin_validJson() throws Exception
	{
		String sessionId = "loginSessionId123";
		when(mockLoginDao.login(Mockito.any(User.class))).thenReturn(sessionId);
		String url = LoginResource.class.getAnnotation(Path.class).value();

		Response response = target(url).request().post(null);
		String actualEntity = response.readEntity(String.class);
		String expectedEntity = "\"loginSessionId123\"";

		assertThat("the sessionId should be valid json (in double quotes)",
				actualEntity, is(expectedEntity));
	}
}
