CREATE OR REPLACE FUNCTION update_hive_inspection(
	IN id INT,
	IN queen_seen BIT(1),
	IN eggs_seen BIT(1),
	IN laying_pattern_stars INT,
	IN temperament_stars INT,
	IN queen_cells INT,
	IN supersedure_cells INT,
	IN swarm_cells INT,
	IN comb_building_stars INT,
	IN frames_sealed_brood INT,
	IN frames_open_brood INT,
	IN frames_honey INT,
	IN weather VARCHAR(255),
	IN temperature_f INT,
	IN wind_speed_mph INT)
RETURNS VOID
AS
$body$
	UPDATE hive_inspections AS hi
		SET queen_seen = queen_seen
			, eggs_seen = eggs_seen
			, laying_pattern_stars = laying_pattern_stars
			, temperament_stars = temperament_stars
			, supersedure_cells = supersedure_cells
			, swarm_cells = swarm_cells
			, comb_building_stars = comb_building_stars
			, frames_sealed_brood = frames_sealed_brood
			, frames_open_brood = frames_open_brood
			, frames_honey = frames_honey
			, weather = weather
			, temperature_f = temperature_f
			, wind_speed_mph = wind_speed_mph
		WHERE hi.id = id;
$body$
LANGUAGE SQL;
