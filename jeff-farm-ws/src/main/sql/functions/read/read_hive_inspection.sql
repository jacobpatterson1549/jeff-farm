DROP FUNCTION IF EXISTS read_hive_inspection;
CREATE FUNCTION read_hive_inspection
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF hive_inspections_readonly
AS
$body$
	BEGIN
		IF permission_check_hive_inspection(set_user_id(read_hive_inspection.user_id), read_hive_inspection.id) THEN
			RETURN QUERY
			SELECT
				  hi.id
				, hi.group_id
				, hi.target_id
				, h.name
				, hi.queen_seen
				, hi.eggs_seen
				, hi.laying_pattern_stars
				, hi.temperament_stars
				, hi.queen_cells
				, hi.supersedure_cells
				, hi.swarm_cells
				, hi.comb_building_stars
				, hi.frames_sealed_brood
				, hi.frames_open_brood
				, hi.frames_honey
				, hi.weather
				, hi.temperature_f
				, hi.wind_speed_mph
				, hi.created_date
				, hi.modified_date
			FROM hive_inspections AS hi
			JOIN hives AS h ON hi.target_id = h.id
			WHERE hi.id = read_hive_inspection.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
