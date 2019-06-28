DROP FUNCTION IF EXISTS update_livestock_coordinate;
CREATE FUNCTION update_livestock_coordinate
	( IN user_id INT
	, IN id INT
	, IN latitude DOUBLE PRECISION
	, IN longitude DOUBLE PRECISION
	, IN display_order INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_livestock_coordinate(set_user_id(update_livestock_coordinate.user_id), update_livestock_coordinate.id) THEN
			UPDATE livestock_coordinates AS cc
				SET
					  latitude = update_livestock_coordinate.latitude
					, longitude = update_livestock_coordinate.longitude
					, display_order = update_livestock_coordinate.display_order
				WHERE cc.id = update_livestock_coordinate.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
