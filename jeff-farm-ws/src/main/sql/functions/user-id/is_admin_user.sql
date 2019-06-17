DROP FUNCTION IF EXISTS is_admin_user;
CREATE FUNCTION is_admin_user
	( IN user_id INT
	)
RETURNS BOOLEAN
AS
$body$
	BEGIN
		RETURN
		EXISTS
		(
			SELECT ur.user_name, ur.role_name
			FROM users AS u
			JOIN user_roles AS ur ON u.user_name = ur.user_name
			WHERE u.id = is_admin_user.user_id
				AND ur.role_name = 'admin'
		);
	END
$body$
LANGUAGE plpgsql;
