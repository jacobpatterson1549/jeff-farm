package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.model.FarmPermission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FarmPermissionDaoTest
{
	@Test
	public void testCreateFarmPermission_nonexistentUserName() throws SQLException
	{
		DataSource mockDataSource = mock(DataSource.class);
		UserIdDao mockUserIdDao = mock(UserIdDao.class);
		Connection mockConnection = mock(Connection.class);
		PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
		ResultSet mockResultSet = mock(ResultSet.class);
		when(mockDataSource.getConnection()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.execute()).thenReturn(true);
		when(mockPreparedStatement.getResultSet()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(false); // fred does not exist
		when(mockResultSet.getString(any(String.class))).thenReturn("encrypted current password");
		FarmPermissionDao farmPermissionDao = new FarmPermissionDao(mockDataSource, mockUserIdDao);
		FarmPermission farmPermission = new FarmPermission()
			.setParentId(14)
			.setUserName("fred");

		try
		{
			int id = farmPermissionDao.create(farmPermission);

			fail("Exception should be thrown when trying to add farm permission for an invalid username.  id=" + id);
		}
		catch (JeffFarmWsException ex)
		{
			assertThat(ex.getMessage(), containsString("No user with username."));
		}

		verify(mockConnection, times(1)).prepareStatement(any(String.class));
	}
}
