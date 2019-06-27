DROP FUNCTION IF EXISTS read_cattle_coordinate;
CREATE FUNCTION read_cattle_coordinate
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF cattle_coordinates
AS
$body$
	BEGIN
		IF permission_check_cattle_coordinate(set_user_id(read_cattle_coordinate.user_id), read_cattle_coordinate.id) THEN
			RETURN QUERY
			SELECT
				  cc.id
				, cc.group_id
				, cc.latitude
				, cc.longitude
				, cc.display_order
				, cc.created_date
				, cc.modified_date
			FROM cattle_coordinates AS cc
			WHERE cc.id = read_cattle_coordinate.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
