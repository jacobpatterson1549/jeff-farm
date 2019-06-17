DROP FUNCTION IF EXISTS read_user_from_user_name;
CREATE FUNCTION read_user_from_user_name
	( IN user_name VARCHAR(20)
	)
RETURNS SETOF users
AS
$body$
	BEGIN
		RETURN QUERY
		SELECT
			  u.id
			, u.user_name
			, CAST(NULL AS CHAR(86)) -- password
			, u.first_name
			, u.last_name
			, u.created_date
			, u.modified_date
		FROM users AS u
		WHERE u.user_name = read_user_from_user_name.user_name;
	END
$body$
LANGUAGE plpgsql;
