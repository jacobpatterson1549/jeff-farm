-- DELIMITER //

DROP PROCEDURE IF EXISTS update_hive_inspection//

CREATE PROCEDURE update_hive_inspection (
	IN hive_id INT,
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
	IN wind_speed_mph INT,
	OUT id INT)

	BEGIN
		INSERT INTO hive_inspections (hive_id
			, queen_seen
			, eggs_seen
			, laying_pattern_starts
			, temperament_stars
			, supersedure_cells
			, swarm_cells
			, comb_building_stars
			, frames_sealed_brood
			, frames_open_brood
			, frames_honey
			, weather
			, temperature_f
			, wind_speed_mph)
		SELECT hive_id
			, queen_seen
			, eggs_seen
			, laying_pattern_starts
			, temperament_stars
			, supersedure_cells
			, swarm_cells
			, comb_building_stars
			, frames_sealed_brood
			, frames_open_brood
			, frames_honey
			, weather
			, temperature_f
			, wind_speed_mph
		FROM hives AS h
		WHERE h.id = hive_id
			AND h.active = 1;

		SET id = LAST_INSERT_ID();
	END//

-- DELIMITER ;
