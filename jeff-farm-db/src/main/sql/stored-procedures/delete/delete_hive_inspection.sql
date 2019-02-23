-- DELIMITER //

DROP PROCEDURE IF EXISTS delete_hive_inspection//

CREATE PROCEDURE delete_hive_inspection (
	IN id INT,
	IN hive_id INT,
	IN farm_id INT)

	BEGIN
		UPDATE hive_inspections AS hi
		JOIN hives AS h ON h.id = hi.hive_id
		SET hi.active = 0
		WHERE hi.id = id
			AND h.id = hive_id
			AND h.farm_id = farm_id;
	END//

-- DELIMITER ;
