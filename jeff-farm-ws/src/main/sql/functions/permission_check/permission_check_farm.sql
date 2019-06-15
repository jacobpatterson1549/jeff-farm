DROP FUNCTION IF EXISTS permission_check_farm;
CREATE FUNCTION permission_check_farm
	( IN user_id INT
	, IN farm_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN FALSE ELSE TRUE END INTO permission_check
			FROM farm_permissions AS fp
			WHERE fp.user_id = permission_check_farm.user_id
				AND fp.farm_id = permission_check_farm.farm_id;
		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to farm %.', user_id, farm_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
