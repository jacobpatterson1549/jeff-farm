CREATE OR REPLACE FUNCTION create_user(
	IN user_name VARCHAR(20),
	IN user_password VARCHAR(86),
	IN first_name VARCHAR(255),
	IN last_name VARCHAR(255))
RETURNS INT
AS
$body$
	INSERT INTO user_roles (user_name, role_name)
	VALUES (user_name, 'user'); -- TODO: ensure this rolled back if the next insert fails.

	INSERT INTO users (user_name, user_password, first_name, last_name)
	VALUES (user_name, user_password, first_name, last_name)
	RETURNING id;
$body$
LANGUAGE SQL;
