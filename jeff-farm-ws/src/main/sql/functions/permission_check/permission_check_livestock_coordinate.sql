DROP FUNCTION IF EXISTS permission_check_livestock_coordinate;
CREATE FUNCTION permission_check_livestock_coordinate
	( IN user_id INT
	, IN livestock_coordinate_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, cc.id
			FROM farm_permissions AS fp
			JOIN livestock_maps AS cm ON fp.farm_id = cm.farm_id
			JOIN livestock_coordinates AS cc ON cm.id = cc.map_id
			WHERE fp.user_id = permission_check_livestock_coordinate.user_id
				AND cc.id = permission_check_livestock_coordinate.livestock_coordinate_id
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to livestock coordinate %.', permission_check_livestock_coordinate.user_id, permission_check_livestock_coordinate.livestock_coordinate_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
