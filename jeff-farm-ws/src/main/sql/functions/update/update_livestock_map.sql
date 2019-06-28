DROP FUNCTION IF EXISTS update_livestock_map;
CREATE FUNCTION update_livestock_map
	( IN user_id INT
	, IN id INT
	, IN livestock_id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_livestock_map(set_user_id(update_livestock_map.user_id), update_livestock_map.id)
			AND permission_check_livestock(update_livestock_map.user_id, update_livestock_map.livestock_id)
			AND
				(SELECT cm.farm_id FROM livestock_maps AS cm WHERE cm.id = update_livestock_map.id)
				=
				(SELECT c.farm_id FROM livestock AS c WHERE c.id = update_livestock_map.livestock_id)
		THEN
			UPDATE livestock_maps AS cm
				SET
					  livestock_id = update_livestock_map.livestock_id
				WHERE cm.id = update_livestock_map.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
