DROP FUNCTION IF EXISTS delete_user;
CREATE FUNCTION delete_user
	( IN id INT
	, IN user_id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM user_roles AS ur
	USING users AS u
	WHERE permission_check_user(set_user_id(user_id), id)
		AND u.user_name = ur.user_name AND u.id = id;

	DELETE
	FROM users AS u
	WHERE u.id = delete_user.id;
$body$
LANGUAGE SQL;
