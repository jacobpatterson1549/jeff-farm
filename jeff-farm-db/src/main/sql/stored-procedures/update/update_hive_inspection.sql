-- DELIMITER $$

DROP PROCEDURE IF EXISTS update_hive_inspection$$

CREATE PROCEDURE update_hive_inspection (
	IN id INT,
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
	IN wind_speed_mph INT)

	BEGIN
		UPDATE hive_inspections AS hi
		JOIN hives AS h ON h.id = hi.hive_id
		SET hi.queen_seen = queen_seen
			, hi.eggs_seen = eggs_seen
			, hi.laying_pattern_stars = laying_pattern_stars
			, hi.temperament_stars = temperament_stars
			, hi.supersedure_cells = supersedure_cells
			, hi.swarm_cells = swarm_cells
			, hi.comb_building_stars = comb_building_stars
			, hi.frames_sealed_brood = frames_sealed_brood
			, hi.frames_open_brood = frames_open_brood
			, hi.frames_honey = frames_honey
			, hi.weather = weather
			, hi.temperature_f = temperature_f
			, hi.wind_speed_mph = wind_speed_mph
		WHERE hi.id = id
			AND hi.active = 1
			AND h.id = hive_id;
	END$$

-- DELIMITER ;
