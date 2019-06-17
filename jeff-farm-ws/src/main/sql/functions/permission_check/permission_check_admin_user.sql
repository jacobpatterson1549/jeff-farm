DROP FUNCTION IF EXISTS permission_check_admin_user;
CREATE FUNCTION permission_check_admin_user
	( IN user_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT is_admin_user(permission_check_admin_user.user_id) INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % is not an admin user.', permission_check_admin_user.user_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
