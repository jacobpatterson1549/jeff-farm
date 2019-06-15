DROP FUNCTION IF EXISTS update_user;
CREATE FUNCTION update_user
	( IN id INT
	, IN user_id INT
	, IN user_password CHAR(86)
	, IN first_name VARCHAR(255)
	, IN last_name VARCHAR(255))
RETURNS VOID
AS
$body$
	UPDATE users AS u
		SET
			  user_password = update_user.user_password
			, first_name = update_user.first_name
			, last_name = update_user.last_name
		WHERE permission_check_user(set_user_id(user_id), id)
			AND u.id = id;
$body$
LANGUAGE SQL;
