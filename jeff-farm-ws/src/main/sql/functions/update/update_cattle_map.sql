DROP FUNCTION IF EXISTS update_cattle_map;
CREATE FUNCTION update_cattle_map
	( IN user_id INT
	, IN id INT
	, IN target_id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_cattle_map(set_user_id(update_cattle_map.user_id), update_cattle_map.id)
			AND permission_check_cattle(update_cattle_map.user_id, update_cattle_map.target_id)
			AND
				(SELECT cm.farm_id FROM cattle_maps AS cm WHERE cm.id = update_cattle_map.id)
				=
				(SELECT c.farm_id FROM cattle AS c WHERE c.id = update_cattle_map.target_id)
		THEN
			UPDATE cattle_maps AS cm
				SET
					  target_id = update_cattle_map.target_id
				WHERE cm.id = update_cattle_map.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
