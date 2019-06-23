-- DROP TABLE hive_inspections_readonly;
-- SELECT  * FROM hive_inspections_readonly LIMIT 0;

CREATE TABLE IF NOT EXISTS hive_inspections_readonly
	( id INT
	, group_id INT
	, target_id INT
	, target_name VARCHAR(255)
	, queen_seen BOOLEAN
	, eggs_seen BOOLEAN
	, laying_pattern_stars INT
	, temperament_stars INT
	, queen_cells INT
	, supersedure_cells INT
	, swarm_cells INT
	, comb_building_stars INT
	, frames_sealed_brood INT
	, frames_open_brood INT
	, frames_honey INT
	, weather VARCHAR(255)
	, temperature_f INT
	, wind_speed_mph INT
	, created_date TIMESTAMP
	, modified_date TIMESTAMP
	);
