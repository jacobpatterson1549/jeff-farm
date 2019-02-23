-- DELIMITER //

DROP PROCEDURE IF EXISTS update_hive//

CREATE PROCEDURE update_hive (
	IN id INT,
	IN farm_id INT,
	IN name VARCHAR(255))

	BEGIN
		UPDATE hives AS h
		SET h.name = name
		WHERE h.id = id
			AND h.farm_id = farm_id;
	END//

-- DELIMITER ;
