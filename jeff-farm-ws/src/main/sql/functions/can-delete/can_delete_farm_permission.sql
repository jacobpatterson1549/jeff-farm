DROP FUNCTION IF EXISTS can_delete_farm_permission;
CREATE FUNCTION can_delete_farm_permission
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT CASE WHEN COUNT(*) > 1 THEN TRUE ELSE FALSE END
		FROM farm_permissions AS fp
		JOIN farm_permissions AS fp2 ON fp.farm_id = fp2.farm_id
		WHERE permission_check_farm_permission(set_user_id(can_delete_farm_permission.user_id), can_delete_farm_permission.id)
			AND fp.id = can_delete_farm_permission.id
$body$
LANGUAGE SQL;
