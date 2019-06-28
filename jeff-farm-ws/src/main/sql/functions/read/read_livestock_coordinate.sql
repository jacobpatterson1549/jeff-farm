DROP FUNCTION IF EXISTS read_livestock_coordinate;
CREATE FUNCTION read_livestock_coordinate
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF livestock_coordinates
AS
$body$
	BEGIN
		IF permission_check_livestock_coordinate(set_user_id(read_livestock_coordinate.user_id), read_livestock_coordinate.id) THEN
			RETURN QUERY
			SELECT
				  cc.id
				, cc.map_id
				, cc.latitude
				, cc.longitude
				, cc.display_order
				, cc.created_date
				, cc.modified_date
			FROM livestock_coordinates AS cc
			WHERE cc.id = read_livestock_coordinate.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
