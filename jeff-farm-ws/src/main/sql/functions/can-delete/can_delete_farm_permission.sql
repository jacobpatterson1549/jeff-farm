DROP FUNCTION IF EXISTS can_delete_farm_permission;
CREATE FUNCTION can_delete_farm_permission
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT permission_check_farm_permission(set_user_id(can_delete_farm_permission.user_id), can_delete_farm_permission.id)
			AND
		(
			SELECT COUNT(*)
			FROM farm_permissions AS fp
			JOIN farm_permissions AS fp2 ON fp.farm_id = fp2.farm_id
			WHERE fp.id = can_delete_farm_permission.id
		)
		> 1
		INTO can_delete_farm_permission.can_delete;
	END
$body$
LANGUAGE plpgsql;
