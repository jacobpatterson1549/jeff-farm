DROP FUNCTION IF EXISTS delete_user;
CREATE FUNCTION delete_user
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_user(set_user_id(delete_user.user_id), delete_user.id) THEN
			DELETE
			FROM user_roles AS ur
			USING users AS u
			WHERE u.user_name = ur.user_name AND u.id = delete_user.id;

			DELETE
				FROM farm_permissions AS fp
				WHERE fp.user_id = delete_user.id;

			DELETE
			FROM users AS u
			WHERE u.id = delete_user.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
