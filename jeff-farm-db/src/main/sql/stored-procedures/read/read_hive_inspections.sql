--DELIMITER //

DROP PROCEDURE IF EXISTS read_hive_inspections//

CREATE PROCEDURE read_hive_inspections (
	IN hive_id INT,
	IN farm_id INT)

	BEGIN
		SELECT hi.hive_id
			, hi.queen_seen
			, hi.eggs_seen
			, hi.laying_pattern_stars
			, hi.temperament_stars
			, hi.supersedure_cells
			, hi.swarm_cells
			, hi.comb_building_stars
			, hi.frames_sealed_brood
			, hi.frames_open_brood
			, hi.frames_honey
			, hi.weather
			, hi.temperature_f
			, hi.wind_speed_mph
		FROM hive_inspections AS hi
		JOIN hives AS h ON h.id = hi.hive_id
		WHERE hi.active = 1
			AND h.id = hive_id
			AND h.farm_id = farm_id;
	END//

-- DELIMITER ;
