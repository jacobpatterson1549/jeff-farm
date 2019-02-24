-- DELIMITER //

DROP PROCEDURE IF EXISTS create_hive//

CREATE PROCEDURE create_hive (
	IN farm_id INT,
	IN name VARCHAR(255),
	OUT id INT)

	BEGIN
		INSERT INTO hives (farm_id, name)
		SELECT farm_id, name
		FROM farms AS f
		WHERE f.id = farm_id
			AND f.active = 1;

		SET id = LAST_INSERT_ID();
	END//

-- DELIMITER ;
