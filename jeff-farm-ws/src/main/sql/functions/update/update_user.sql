DROP FUNCTION IF EXISTS update_user;
CREATE FUNCTION update_user
	( IN user_id INT
	, IN id INT
	, IN first_name VARCHAR(255)
	, IN last_name VARCHAR(255)
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_user(set_user_id(update_user.user_id), update_user.id) THEN
			UPDATE users AS u
				SET
					  first_name = update_user.first_name
					, last_name = update_user.last_name
				WHERE u.id = update_user.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
