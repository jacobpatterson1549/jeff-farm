DROP FUNCTION IF EXISTS permission_check_user;
CREATE FUNCTION permission_check_user
	( IN user_id INT
	, IN target_user_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT user_id = target_user_id INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to user %.', permission_check_user.user_id, permission_check_user.target_user_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
