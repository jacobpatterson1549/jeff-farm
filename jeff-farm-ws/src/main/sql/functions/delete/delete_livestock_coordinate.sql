DROP FUNCTION IF EXISTS delete_livestock_coordinate;
CREATE FUNCTION delete_livestock_coordinate
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_livestock_coordinate(set_user_id(delete_livestock_coordinate.user_id), delete_livestock_coordinate.id) THEN
			DELETE
			FROM livestock_coordinates AS cc
			WHERE cc.id = delete_livestock_coordinate.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
