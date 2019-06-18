package com.github.ants280.jeff.farm.ws.resources;

import com.github.ants280.jeff.farm.ws.dao.LoginDao;
import com.github.ants280.jeff.farm.ws.dao.UserDao;
import java.util.Arrays;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class UserResourceTest extends JerseyTest
{
	private final boolean isAdmin; // TODO: set this value
	private final int expectedStatus;

	public UserResourceTest(boolean isAdmin, int expectedStatus)
	{
		this.isAdmin = isAdmin;
		this.expectedStatus = expectedStatus;
	}

	@Parameterized.Parameters(name = "{index}: isAdmin:{0}, expectedStatus:{1}")
	public static Iterable<Object[]> data()
	{
		return Arrays.asList(new Object[]{true, 200}, new Object[]{false, 403});
	}

	@Override
	protected Application configure()
	{
		return new ResourceConfig().register(UserResource.class)
			.register(new AbstractBinder()
			{
				@Override
				protected void configure()
				{
					bind(mock(UserDao.class)).to(UserDao.class);
					bind(mock(LoginDao.class)).to(LoginDao.class);
				}
			});
	}

//	@Override
//	public TestContainerFactory getTestContainerFactory()
//	{
//		return new GrizzlyWebTestContainerFactory();
//	}
//
//	@Override
//	public DeploymentContext configureDeployment()
//	{
//		return ServletDeploymentContext.forServlet(new ServletContainer(
//			resourceConfig)).build();
//	}

	@Test
	public void testCanGetUserList()
	{
		Response response = target("/user/list").request().get();

		int actualStatus = response.getStatus();

		assertThat(actualStatus, is(expectedStatus));
	}
}
