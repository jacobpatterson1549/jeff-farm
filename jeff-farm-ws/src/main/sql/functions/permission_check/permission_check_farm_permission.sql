DROP FUNCTION IF EXISTS permission_check_farm_permission;
CREATE FUNCTION permission_check_farm_permission
	( IN user_id INT
	, IN id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN FALSE ELSE TRUE END INTO permission_check
		FROM farm_permissions AS fp
		WHERE permission_check_farm(permission_check_farm_permission.user_id, fp.farm_id)
			AND fp.id = permission_check_farm_permission.id;
	END
$body$
LANGUAGE plpgsql;
