-- DELIMITER $$

DROP PROCEDURE IF EXISTS delete_hive_inspection$$

CREATE PROCEDURE delete_hive_inspection (
	IN id INT)

	BEGIN
		DELETE hi
		FROM hive_inspections AS hi
		WHERE hi.id = id;
	END$$

-- DELIMITER ;
