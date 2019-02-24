-- DELIMITER //

DROP PROCEDURE IF EXISTS update_hive//

CREATE PROCEDURE update_hive (
	IN id INT,
	IN name VARCHAR(255))

	BEGIN
		UPDATE hives AS h
		SET h.name = name
		WHERE h.id = id
			AND h.active = 1;
	END//

-- DELIMITER ;
