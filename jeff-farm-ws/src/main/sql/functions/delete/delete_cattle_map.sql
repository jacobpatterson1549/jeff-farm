DROP FUNCTION IF EXISTS delete_cattle_map;
CREATE FUNCTION delete_cattle_map
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_cattle_map(set_user_id(delete_cattle_map.user_id), delete_cattle_map.id) THEN
			DELETE
			FROM cattle_maps AS cm
			WHERE cm.id = delete_cattle_map.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
