DROP FUNCTION IF EXISTS create_livestock_coordinate;
CREATE FUNCTION create_livestock_coordinate
	( IN user_id INT
	, IN map_id INT
	, IN latitude DOUBLE PRECISION
	, IN longitude DOUBLE PRECISION
	, IN display_order INT
	, OUT id INT
	)
AS
$body$
	BEGIN
		IF permission_check_livestock_map(set_user_id(create_livestock_coordinate.user_id), create_livestock_coordinate.map_id) THEN
			INSERT INTO livestock_coordinates
				( map_id
				, latitude
				, longitude
				, display_order
				)
			SELECT
				  create_livestock_coordinate.map_id
				, create_livestock_coordinate.latitude
				, create_livestock_coordinate.longitude
				, create_livestock_coordinate.display_order
			FROM livestock_maps AS cm
			WHERE cm.id = create_livestock_coordinate.map_id
			RETURNING LASTVAL()
			INTO create_livestock_coordinate.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
