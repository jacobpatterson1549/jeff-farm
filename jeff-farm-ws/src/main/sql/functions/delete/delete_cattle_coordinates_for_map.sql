DROP FUNCTION IF EXISTS delete_cattle_coordinates_for_map;
CREATE FUNCTION delete_cattle_coordinates_for_map
	( IN user_id INT
	, IN map_id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_cattle_map(set_user_id(delete_cattle_coordinates_for_map.user_id), delete_cattle_coordinates_for_map.map_id) THEN
			DELETE
			FROM cattle_coordinates AS cc
			WHERE cc.map_id = delete_cattle_coordinates_for_map.map_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
