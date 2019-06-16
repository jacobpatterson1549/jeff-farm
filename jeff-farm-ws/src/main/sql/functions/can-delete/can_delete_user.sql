DROP FUNCTION IF EXISTS can_delete_user;
CREATE FUNCTION can_delete_user
    ( IN user_id INT -- the user requesting the delete
    , IN id INT -- the user to delete
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END
		FROM farm_permissions AS fp
		WHERE permission_check_user(set_user_id(can_delete_user.user_id), can_delete_user.id)
			AND fp.user_id = can_delete_user.id
$body$
LANGUAGE SQL;
