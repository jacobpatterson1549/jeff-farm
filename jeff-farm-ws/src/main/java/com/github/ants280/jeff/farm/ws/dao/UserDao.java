package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.PasswordGenerator;
import com.github.ants280.jeff.farm.ws.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jvnet.hk2.annotations.Contract;
import org.springframework.jdbc.core.RowMapper;

@Contract // TODO: is this needed?
public class UserDao extends StoredProcedureDao implements CrudDao<User>
{
	private final PasswordGenerator passwordGenerator;

	@Inject
	public UserDao(DataSource dataSource, PasswordGenerator passwordGenerator)
	{
		super(dataSource);
		
		this.passwordGenerator = passwordGenerator;
	}

	@Override
	public int create(User user)
	{
		String password
				= passwordGenerator.getHashedPassword(user.getPassword());

		return this.executeCreate(
				"create_user",
				Arrays.asList(
						new Parameter<>(User.USER_NAME_COLUMN, user.getUserName(), Types.VARCHAR),
						new Parameter<>(User.PASSWORD_COLUMN, password, Types.VARCHAR),
						new Parameter<>(User.FIRST_NAME_COLUMN, user.getFirstName(), Types.VARCHAR),
						new Parameter<>(User.LAST_NAME_COLUMN, user.getLastName(), Types.VARCHAR)),
				User.ID_COLUMN);
	}

	@Override
	public User read(int id)
	{
		return this.executeRead(
				"read_user",
				Collections.singletonList(
						new Parameter<>(User.ID_COLUMN, id, Types.INTEGER)),
				new ResultSetExtractor());
	}

	public User read(String userName)
	{
		return this.executeRead(
				"read_user_from_user_name",
				Collections.singletonList(
						new Parameter<>(User.USER_NAME_COLUMN, userName, Types.VARCHAR)),
				new ResultSetExtractor());
	}

	@Override
	public List<User> readList(int parentId)
	{
		throw new IllegalArgumentException("Not allowed");
	}

	@Override
	public void update(User user)
	{
		this.executeUpdate(
				"update_user",
				Arrays.asList(
						new Parameter<>(User.FIRST_NAME_COLUMN, user.getFirstName(), Types.VARCHAR),
						new Parameter<>(User.LAST_NAME_COLUMN, user.getLastName(), Types.VARCHAR)));
	}

	@Override
	public void delete(int id)
	{
		this.executeUpdate(
				"delete_user",
				Collections.singletonList(
						new Parameter<>(User.ID_COLUMN, id, Types.INTEGER)));
	}

	@Override
	public boolean canDelete(int id)
	{
		return true;
	}

	public static class ResultSetExtractor implements RowMapper<User>
	{
		@Override
		public User mapRow(ResultSet rs, int i) throws SQLException
		{
			return new User(
					rs.getInt(User.ID_COLUMN),
					rs.getString(User.USER_NAME_COLUMN),
					null, // password
					rs.getString(User.FIRST_NAME_COLUMN),
					rs.getString(User.LAST_NAME_COLUMN),
					rs.getTimestamp(User.CREATED_DATE_COLUMN),
					rs.getTimestamp(User.MODIFIED_DATE_COLUMN));
		}
	}
}
