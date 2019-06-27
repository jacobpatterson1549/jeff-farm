DROP FUNCTION IF EXISTS can_delete_cattle;
CREATE FUNCTION can_delete_cattle
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT permission_check_cattle(set_user_id(can_delete_cattle.user_id), can_delete_cattle.id)
			AND
		NOT EXISTS
		(
			SELECT cm.target_id
			FROM cattle_maps AS cm
			WHERE cm.target_id = can_delete_cattle.id
		)
		INTO can_delete_cattle.can_delete;
	END
$body$
LANGUAGE plpgsql;
