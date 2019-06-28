DROP FUNCTION IF EXISTS create_cattle_coordinate;
CREATE FUNCTION create_cattle_coordinate
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
		IF permission_check_cattle_map(set_user_id(create_cattle_coordinate.user_id), create_cattle_coordinate.map_id) THEN
			INSERT INTO cattle_coordinates
				( map_id
				, latitude
				, longitude
				, display_order
				)
			SELECT
				  create_cattle_coordinate.map_id
				, create_cattle_coordinate.latitude
				, create_cattle_coordinate.longitude
				, create_cattle_coordinate.display_order
			FROM cattle_maps AS cm
			WHERE cm.id = create_cattle_coordinate.group_id
				AND cm.farm_id = p.farm_id
			RETURNING LASTVAL()
			INTO create_cattle_coordinate.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
