--DELIMITER $$

-- DROP TABLE hive_inspections_audit$$
-- SELECT  * FROM hive_inspections_audit LIMIT 0$$

CREATE TABLE IF NOT EXISTS hive_inspections_audit
(
	audit_id INT PRIMARY KEY AUTO_INCREMENT,
	action_type CHAR(1) NOT NULL, -- i (insert), b (before update), a (after update), d (delete)
-- 	userId INT,
	action_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	id INT,
	hive_id INT,
	queen_seen BIT(1),
	eggs_seen BIT(1),
	laying_pattern_stars INT,
	temperament_stars INT,
	queen_cells INT,
	supersedure_cells INT,
	swarm_cells INT,
	comb_building_stars INT,
	frames_sealed_brood INT,
	frames_open_brood INT,
	frames_honey INT,
	weather VARCHAR(255),
	temperature_f INT,
	wind_speed_mph INT
)$$

DROP PROCEDURE IF EXISTS hive_inspection_changed_function$$
CREATE PROCEDURE hive_inspection_changed_function (
	IN action_type CHAR(1),
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
		INSERT INTO hive_inspections_audit (
			action_type,
			id,
			hive_id,
			queen_seen,
			eggs_seen,
			laying_pattern_stars,
			temperament_stars,
			queen_cells,
			supersedure_cells,
			swarm_cells,
			comb_building_stars,
			frames_sealed_brood,
			frames_open_brood,
			frames_honey,
			weather,
			temperature_f,
			wind_speed_mph)
		VALUES (
			action_type,
			id,
			hive_id,
			queen_seen,
			eggs_seen,
			laying_pattern_stars,
			temperament_stars,
			queen_cells,
			supersedure_cells,
			swarm_cells,
			comb_building_stars,
			frames_sealed_brood,
			frames_open_brood,
			frames_honey,
			weather,
			temperature_f,
			wind_speed_mph);
	END$$

DROP TRIGGER IF EXISTS hive_inspection_inserted_trigger$$
CREATE TRIGGER hive_inspection_inserted_trigger
	AFTER INSERT
	ON hive_inspections
	FOR EACH ROW
	BEGIN
		CALL hive_inspection_changed_function(
			'i',
			NEW.id,
			NEW.hive_id,
			NEW.queen_seen,
			NEW.eggs_seen,
			NEW.laying_pattern_stars,
			NEW.temperament_stars,
			NEW.queen_cells,
			NEW.supersedure_cells,
			NEW.swarm_cells,
			NEW.comb_building_stars,
			NEW.frames_sealed_brood,
			NEW.frames_open_brood,
			NEW.frames_honey,
			NEW.weather,
			NEW.temperature_f,
			NEW.wind_speed_mph);
	END$$

DROP TRIGGER IF EXISTS hive_inspection_updated_trigger$$
CREATE TRIGGER hive_inspection_updated_trigger
	AFTER UPDATE
	ON hive_inspections
	FOR EACH ROW
	BEGIN
		CALL hive_inspection_changed_function(
			'b',
			OLD.id,
			OLD.hive_id,
			OLD.queen_seen,
			OLD.eggs_seen,
			OLD.laying_pattern_stars,
			OLD.temperament_stars,
			OLD.queen_cells,
			OLD.supersedure_cells,
			OLD.swarm_cells,
			OLD.comb_building_stars,
			OLD.frames_sealed_brood,
			OLD.frames_open_brood,
			OLD.frames_honey,
			OLD.weather,
			OLD.temperature_f,
			OLD.wind_speed_mph);
		CALL hive_inspection_changed_function(
			'a',
			NEW.id,
			NEW.hive_id,
			NEW.queen_seen,
			NEW.eggs_seen,
			NEW.laying_pattern_stars,
			NEW.temperament_stars,
			NEW.queen_cells,
			NEW.supersedure_cells,
			NEW.swarm_cells,
			NEW.comb_building_stars,
			NEW.frames_sealed_brood,
			NEW.frames_open_brood,
			NEW.frames_honey,
			NEW.weather,
			NEW.temperature_f,
			NEW.wind_speed_mph);
	END$$

DROP TRIGGER IF EXISTS hive_inspection_deleted_trigger$$
CREATE TRIGGER hive_inspection_deleted_trigger
	AFTER DELETE
	ON hive_inspections
	FOR EACH ROW
	BEGIN
		CALL hive_inspection_changed_function(
			'd',
			OLD.id,
			OLD.hive_id,
			OLD.queen_seen,
			OLD.eggs_seen,
			OLD.laying_pattern_stars,
			OLD.temperament_stars,
			OLD.queen_cells,
			OLD.supersedure_cells,
			OLD.swarm_cells,
			OLD.comb_building_stars,
			OLD.frames_sealed_brood,
			OLD.frames_open_brood,
			OLD.frames_honey,
			OLD.weather,
			OLD.temperature_f,
			OLD.wind_speed_mph);
	END$$

-- DELIMITER ;