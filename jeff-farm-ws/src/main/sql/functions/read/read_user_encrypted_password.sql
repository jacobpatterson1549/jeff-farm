DROP FUNCTION IF EXISTS read_user_encrypted_password;
CREATE FUNCTION read_user_encrypted_password
	( IN user_id INT
	, IN id INT
	)
RETURNS CHAR(86)
AS
$body$
	BEGIN
		IF permission_check_user(set_user_id(read_user_encrypted_password.user_id), read_user_encrypted_password.id) THEN
			RETURN
			(
				SELECT u.user_password
				FROM users AS u
				WHERE u.id = read_user_encrypted_password.id
			);
		END IF;
	END
$body$
LANGUAGE plpgsql;
