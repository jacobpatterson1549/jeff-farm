package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.JeffFarmWsException;
import com.github.ants280.jeff.farm.ws.PasswordGenerator;
import com.github.ants280.jeff.farm.ws.model.UserPasswordReplacement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.sql.DataSource;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class UserDaoTest
{
	private final boolean isAdmin;
	private final boolean currentPasswordCorrect;
	private final boolean expectedUpdateSuccess;
	private final DataSource mockDataSource;
	private final PasswordGenerator mockPasswordGenerator;
	private final UserIdDao mockUserIdDao;
	private final UserDao userDao;

	public UserDaoTest(
		boolean isAdmin,
		boolean currentPasswordCorrect,
		boolean expectedUpdateSuccess)
	{
		this.isAdmin = isAdmin;
		this.currentPasswordCorrect = currentPasswordCorrect;
		this.expectedUpdateSuccess = expectedUpdateSuccess;
		this.mockDataSource = mock(DataSource.class);
		this.mockPasswordGenerator = mock(PasswordGenerator.class);
		this.mockUserIdDao = mock(UserIdDao.class);
		this.userDao = new UserDao(mockDataSource, mockPasswordGenerator, mockUserIdDao);
	}

	@Parameterized.Parameters(name = "{index}: isAdmin:{0}, currentPasswordCorrect:{1}")
	public static Iterable<Object[]> data()
	{
		return Arrays.asList(
			new Object[]{true, true, true},
			new Object[]{true, false, false},
			new Object[]{false, true, true},
			new Object[]{false, false, false});
	}

	@Test
	public void updatePassword() throws SQLException
	{
		Connection mockConnection = mock(Connection.class);
		PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
		ResultSet mockResultSet = mock(ResultSet.class);
		when(mockDataSource.getConnection()).thenReturn(mockConnection);
		when(mockUserIdDao.getUserId()).thenReturn(104);
		when(mockUserIdDao.hasAdimnRole()).thenReturn(isAdmin);
		when(mockPasswordGenerator.isStoredPassword("current password", "encrypted current password")).thenReturn(currentPasswordCorrect);
		when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.execute()).thenReturn(true);
		when(mockPreparedStatement.getResultSet()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true, false);
		when(mockResultSet.getString(any(String.class))).thenReturn("encrypted current password");
		UserPasswordReplacement passwordReplacement = new UserPasswordReplacement()
			.setId(104)
			.setCurrentPassword("current password")
			.setNewPassword("new password");

		try
		{
			userDao.updatePassword(passwordReplacement);

			assertThat("expected to succeed, but did not",
				expectedUpdateSuccess, is(true));
		}
		catch (JeffFarmWsException ex)
		{
			assertThat("expected to fail, but did not: " + ex.getMessage(),
				expectedUpdateSuccess, is(false));
		}

		verify(mockConnection, times(currentPasswordCorrect ? 2 : 1)).prepareStatement(any(String.class));
		verify(mockPasswordGenerator, times(1)).isStoredPassword("current password", "encrypted current password");
		verify(mockUserIdDao, times(0)).hasAdimnRole(); // relic of old version, should not be checked by code (because the CURRENT user id need always be checked)
		verify(mockPasswordGenerator, times(1)).getHashedPassword("new password");
	}
}
