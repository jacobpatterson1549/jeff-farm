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
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	public void testUpdatePassword() throws SQLException
	{
		String currentPassword = "current password";
		String encryptedCurrentPassword = "encrypted current password";
		String newPassword = "new_Password1";
		Connection mockConnection = mock(Connection.class);
		PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
		ResultSet mockResultSet = mock(ResultSet.class);
		when(mockDataSource.getConnection()).thenReturn(mockConnection);
		when(mockUserIdDao.getUserId()).thenReturn(104);
		when(mockUserIdDao.hasAdimnRole()).thenReturn(isAdmin);
		when(mockPasswordGenerator.isStoredPassword(currentPassword, encryptedCurrentPassword)).thenReturn(currentPasswordCorrect);
		when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.execute()).thenReturn(true);
		when(mockPreparedStatement.getResultSet()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true, false);
		when(mockResultSet.getString(any(String.class))).thenReturn(encryptedCurrentPassword);
		UserPasswordReplacement passwordReplacement = new UserPasswordReplacement()
			.setId(104)
			.setCurrentPassword(currentPassword)
			.setNewPassword(newPassword);

		try
		{
			userDao.updatePassword(passwordReplacement);

			assertThat("Expected to fail, but did not.",
				expectedUpdateSuccess, is(true));
		}
		catch (JeffFarmWsException ex)
		{
			assertThat("Expected to succeed, but did not: " + ex.getMessage(),
				expectedUpdateSuccess, is(false));
		}

		verify(mockConnection, times(currentPasswordCorrect ? 2 : 1)).prepareStatement(any(String.class));
		verify(mockPasswordGenerator, times(1)).isStoredPassword(currentPassword, encryptedCurrentPassword);
		verify(mockUserIdDao, times(0)).hasAdimnRole(); // relic of old version, should not be checked by code (because the CURRENT user id need always be checked)
		verify(mockPasswordGenerator, times(1)).getHashedPassword(newPassword);
	}
}
