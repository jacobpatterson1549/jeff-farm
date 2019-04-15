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
		SET queen_seen = update_hive_inspection.queen_seen
			, eggs_seen = update_hive_inspection.eggs_seen
			, laying_pattern_stars = update_hive_inspection.laying_pattern_stars
			, temperament_stars = update_hive_inspection.temperament_stars
			, supersedure_cells = update_hive_inspection.supersedure_cells
			, swarm_cells = update_hive_inspection.swarm_cells
			, comb_building_stars = update_hive_inspection.comb_building_stars
			, frames_sealed_brood = update_hive_inspection.frames_sealed_brood
			, frames_open_brood = update_hive_inspection.frames_open_brood
			, frames_honey = update_hive_inspection.frames_honey
			, weather = update_hive_inspection.weather
			, temperature_f = update_hive_inspection.temperature_f
			, wind_speed_mph = update_hive_inspection.wind_speed_mph
		WHERE hi.id = update_hive_inspection.id;
$body$
LANGUAGE SQL;
