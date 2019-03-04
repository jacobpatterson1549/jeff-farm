-- DELIMITER $$

DROP PROCEDURE IF EXISTS delete_hive$$

CREATE PROCEDURE delete_hive (
	IN id INT)

	BEGIN
		DELETE h
		FROM hives AS h
		WHERE h.id = id;
	END$$

-- DELIMITER ;
