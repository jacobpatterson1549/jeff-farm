DROP FUNCTION IF EXISTS delete_cattle_coordinate;
CREATE FUNCTION delete_cattle_coordinate
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_cattle_coordinate(set_user_id(delete_cattle_coordinate.user_id), delete_cattle_coordinate.id) THEN
			DELETE
			FROM cattle_coordinates AS cc
			WHERE cc.id = delete_cattle_coordinate.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
