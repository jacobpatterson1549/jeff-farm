-- DELIMITER //

DROP PROCEDURE IF EXISTS delete_hive//

CREATE PROCEDURE delete_hive (
	IN id INT,
	IN farm_id INT)

	BEGIN
		UPDATE hives AS h
		SET h.active = 0
		WHERE h.id = id
			AND h.active = 1;
	END//

-- DELIMITER ;
