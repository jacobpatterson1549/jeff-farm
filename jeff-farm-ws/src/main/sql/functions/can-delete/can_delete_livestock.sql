DROP FUNCTION IF EXISTS can_delete_livestock;
CREATE FUNCTION can_delete_livestock
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT permission_check_livestock(set_user_id(can_delete_livestock.user_id), can_delete_livestock.id)
			AND
		NOT EXISTS
		(
			SELECT cm.livestock_id
			FROM livestock_maps AS cm
			WHERE cm.livestock_id = can_delete_livestock.id
		)
		INTO can_delete_livestock.can_delete;
	END
$body$
LANGUAGE plpgsql;
