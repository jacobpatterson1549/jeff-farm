DROP FUNCTION IF EXISTS update_user;
CREATE FUNCTION update_user
	( IN user_id INT
	, IN id INT
	, IN user_password CHAR(86)
	, IN first_name VARCHAR(255)
	, IN last_name VARCHAR(255))
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_user(set_user_id(user_id), update_user.id) THEN
			UPDATE users AS u
				SET
					  user_password = update_user.user_password
					, first_name = update_user.first_name
					, last_name = update_user.last_name
				WHERE u.id = update_user.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
