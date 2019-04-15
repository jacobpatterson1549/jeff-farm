CREATE OR REPLACE FUNCTION read_hive_inspections(IN hive_id INT)
RETURNS SETOF hive_inspections
AS
$body$
	SELECT hi.id
			, hi.hive_id
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
	WHERE hi.hive_id = read_hive_inspections.hive_id;
$body$
LANGUAGE SQL;
