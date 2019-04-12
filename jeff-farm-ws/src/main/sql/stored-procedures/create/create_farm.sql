-- DELIMITER $$

DROP PROCEDURE IF EXISTS create_farm$$

CREATE PROCEDURE create_farm (
	IN name VARCHAR(255),
	IN location VARCHAR(255),
	OUT id INT)

	BEGIN
		INSERT INTO farms (name, location)
		VALUES (name, location);

		SET id = LAST_INSERT_ID();
	END$$

-- DELIMITER ;
