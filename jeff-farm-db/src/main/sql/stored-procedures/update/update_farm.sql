-- DELIMITER $$

DROP PROCEDURE IF EXISTS update_farm$$

CREATE PROCEDURE update_farm (
	IN id INT,
	IN name VARCHAR(255),
	IN location VARCHAR(255))

	BEGIN
		UPDATE farms AS f
		SET f.name = name,
			f.location = location
		WHERE f.id = id
			AND f.active = 1;
	END$$

-- DELIMITER ;
