DROP FUNCTION IF EXISTS create_hive_inspection;
CREATE FUNCTION create_hive_inspection
	( IN user_id INT
	, IN group_id INT
	, IN target_id INT
	, IN queen_seen BOOLEAN
	, IN eggs_seen BOOLEAN
	, IN laying_pattern_stars INT
	, IN temperament_stars INT
	, IN queen_cells INT
	, IN supersedure_cells INT
	, IN swarm_cells INT
	, IN comb_building_stars INT
	, IN frames_sealed_brood INT
	, IN frames_open_brood INT
	, IN frames_honey INT
	, IN weather VARCHAR(255)
	, IN temperature_f INT
	, IN wind_speed_mph INT
	, OUT id INT)
AS
$body$
	BEGIN
		IF permission_check_hive_inspection_group(set_user_id(create_hive_inspection.user_id), create_hive_inspection.group_id) THEN
			INSERT INTO hive_inspections
				( group_id
				, target_id
				, queen_seen
				, eggs_seen
				, laying_pattern_stars
				, temperament_stars
				, queen_cells
				, supersedure_cells
				, swarm_cells
				, comb_building_stars
				, frames_sealed_brood
				, frames_open_brood
				, frames_honey
				, weather
				, temperature_f
				, wind_speed_mph
				)
			SELECT
				  create_hive_inspection.group_id
				, create_hive_inspection.target_id
				, create_hive_inspection.queen_seen
				, create_hive_inspection.eggs_seen
				, create_hive_inspection.laying_pattern_stars
				, create_hive_inspection.temperament_stars
				, create_hive_inspection.queen_cells
				, create_hive_inspection.supersedure_cells
				, create_hive_inspection.swarm_cells
				, create_hive_inspection.comb_building_stars
				, create_hive_inspection.frames_sealed_brood
				, create_hive_inspection.frames_open_brood
				, create_hive_inspection.frames_honey
				, create_hive_inspection.weather
				, create_hive_inspection.temperature_f
				, create_hive_inspection.wind_speed_mph
			FROM hive_inspection_groups AS hig
			JOIN hives AS h ON create_hive_inspection.target_id = h.id
			WHERE hig.id = create_hive_inspection.group_id
				AND hig.farm_id = h.farm_id
			RETURNING LASTVAL()
			INTO create_hive_inspection.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
