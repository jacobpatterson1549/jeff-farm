--DELIMITER //

DROP PROCEDURE IF EXISTS read_hive_inspection//

CREATE PROCEDURE read_hive_inspection (
	IN id INT)

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
			, hi.created_date
			, hi.modified_date
		FROM hive_inspections AS hi
		WHERE hi.id = id
			AND hi.active = 1;
	END//

-- DELIMITER ;
