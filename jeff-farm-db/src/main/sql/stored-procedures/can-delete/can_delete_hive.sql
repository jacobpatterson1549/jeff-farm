-- DELIMITER $$

DROP PROCEDURE IF EXISTS can_delete_hive$$

CREATE PROCEDURE can_delete_hive (
	IN id INT,
	OUT can_delete BIT(1))

	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN 1 ELSE 0 END INTO can_delete
		FROM hive_inspections AS hi
		WHERE hi.hive_id = id;
	END$$

-- DELIMITER ;
