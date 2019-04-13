CREATE OR REPLACE FUNCTION read_user(IN user_name VARCHAR(20))
RETURNS users
AS
$body$
	SELECT u.id
		, u.user_name
		, NULL -- password
		, u.first_name
		, u.last_name
		, u.created_date
		, u.modified_date
	FROM users AS u
	WHERE u.user_name = user_name;
$body$
LANGUAGE SQL;
