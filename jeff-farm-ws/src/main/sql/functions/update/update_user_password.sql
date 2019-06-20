DROP FUNCTION IF EXISTS update_user_password;
CREATE FUNCTION update_user_password
	( IN user_id INT
	, IN id INT
	, IN old_user_password CHAR(86)
	, IN new_user_password CHAR(86)
	, OUT user_password_updated BOOLEAN
	)
AS
$body$
	BEGIN
		IF permission_check_user(set_user_id(update_user_password.user_id), update_user_password.id) THEN
			UPDATE users AS u
				SET user_password = update_user_password.new_user_password
				WHERE u.id = update_user_password.id
					AND (u.user_password = update_user_password.old_user_password
						OR is_admin_user(update_user_password.user_id));
			SELECT FOUND INTO update_user_password.user_password_updated;
		END IF;
	END
$body$
LANGUAGE plpgsql;
