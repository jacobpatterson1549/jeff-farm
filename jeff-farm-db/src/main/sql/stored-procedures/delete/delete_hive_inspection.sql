-- DELIMITER //

DROP PROCEDURE IF EXISTS delete_hive_inspection//

CREATE PROCEDURE delete_hive_inspection (
	IN id INT)

	BEGIN
		UPDATE hive_inspections AS hi
		SET hi.active = 0
		WHERE hi.id = id
			AND hi.active = 1;
	END//

-- DELIMITER ;
