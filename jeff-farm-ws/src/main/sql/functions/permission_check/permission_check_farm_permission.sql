DROP FUNCTION IF EXISTS permission_check_farm_permission;
CREATE FUNCTION permission_check_farm_permission
	( IN user_id INT
	, IN id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, fp.id
			FROM farm_permissions AS fp
			WHERE fp.id = permission_check_farm_permission.id
				AND permission_check_farm(permission_check_farm_permission.user_id, fp.farm_id)
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to farm permission %.', permission_check_farm_permission.user_id, permission_check_farm_permission.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
