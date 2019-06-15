DROP FUNCTION IF EXISTS create_user;
CREATE FUNCTION create_user
	(IN user_name VARCHAR(20)
	, IN user_password VARCHAR(86)
	, IN first_name VARCHAR(255)
	, IN last_name VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	WITH new_user AS (
		INSERT INTO users
			( user_name
			, user_password
			, first_name
			, last_name
			)
		VALUES
			( user_name
			, create_user.user_password
			, create_user.first_name
			, create_user.last_name
			)
		RETURNING id
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
	SELECT id
	FROM new_user;
$body$
LANGUAGE SQL;
