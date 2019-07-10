DROP FUNCTION IF EXISTS create_user;
CREATE FUNCTION create_user
	( IN user_name VARCHAR(20)
	, IN user_password VARCHAR(86)
	, IN first_name VARCHAR(255)
	, IN last_name VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	BEGIN
		IF EXISTS
		(
			SELECT 1 FROM users AS u WHERE u.user_name = create_user.user_name
		)
		THEN
			RAISE EXCEPTION 'Username % already exists.', create_user.user_name;
		END IF;

		WITH new_user (id) AS (
			INSERT INTO users
				( user_name
				, user_password
				, first_name
				, last_name
				)
			SELECT
				  user_name
				, create_user.user_password
				, create_user.first_name
				, create_user.last_name
			WHERE set_user_id(-1) IS NOT NULL
			RETURNING LASTVAL()
		)
		, new_user_role AS (
			INSERT INTO user_roles
				( user_name
				, role_name
				)
			VALUES
				( create_user.user_name
				, 'user'
				)
		)
		SELECT CAST(nu.id AS INT)
		FROM new_user AS nu
		INTO create_user.id;
	END;
$body$
LANGUAGE plpgsql;
