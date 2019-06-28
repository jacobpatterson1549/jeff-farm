DROP FUNCTION IF EXISTS permission_check_cattle_coordinate;
CREATE FUNCTION permission_check_cattle_coordinate
	( IN user_id INT
	, IN cattle_coordinate_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, cc.id
			FROM farm_permissions AS fp
			JOIN cattle_maps AS cm ON fp.farm_id = cm.farm_id
			JOIN cattle_coordinates AS cc ON cm.id = cm.map_id
			WHERE fp.user_id = permission_check_cattle_coordinate.user_id
				AND cm.id = permission_check_cattle_coordinate.cattle_coordinate_id
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to cattle coordinate %.', permission_check_cattle_coordinate.user_id, permission_check_cattle_coordinate.cattle_coordinate_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
