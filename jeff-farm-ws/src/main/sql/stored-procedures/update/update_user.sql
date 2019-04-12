CREATE OR REPLACE FUNCTION update_user(
	IN id INT,
	IN user_password CHAR(86),
	IN first_name VARCHAR(255),
	IN last_name VARCHAR(255))
RETURNS VOID
AS
$body$
	UPDATE users AS u
		SET user_password = user_password,
			first_name = first_name,
			last_name = last_name
		WHERE u.id = id;
$body$
LANGUAGE SQL;
