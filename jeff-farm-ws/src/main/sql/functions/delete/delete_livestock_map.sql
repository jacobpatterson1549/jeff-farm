DROP FUNCTION IF EXISTS delete_livestock_map;
CREATE FUNCTION delete_livestock_map
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_livestock_map(set_user_id(delete_livestock_map.user_id), delete_livestock_map.id) THEN
			DELETE
			FROM livestock_maps AS cm
			WHERE cm.id = delete_livestock_map.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
