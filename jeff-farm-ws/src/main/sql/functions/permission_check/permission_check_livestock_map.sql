DROP FUNCTION IF EXISTS permission_check_livestock_map;
CREATE FUNCTION permission_check_livestock_map
	( IN user_id INT
	, IN livestock_map_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, cm.id
			FROM farm_permissions AS fp
			JOIN livestock_maps AS cm ON fp.farm_id = cm.farm_id
			WHERE fp.user_id = permission_check_livestock_map.user_id
				AND cm.id = permission_check_livestock_map.livestock_map_id
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to livestock map %.', permission_check_livestock_map.user_id, permission_check_livestock_map.livestock_map_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
