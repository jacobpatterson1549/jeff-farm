DROP FUNCTION IF EXISTS read_user;
CREATE FUNCTION read_user(IN id INT)
RETURNS SETOF users
AS
$body$
	SELECT
		  u.id
		, u.user_name
		, NULL -- password
		, u.first_name
		, u.last_name
		, u.created_date
		, u.modified_date
	FROM users AS u
	WHERE u.id = read_user.id;
$body$
LANGUAGE SQL;
