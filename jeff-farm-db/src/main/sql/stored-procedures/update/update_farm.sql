-- DELIMITER //

DROP PROCEDURE IF EXISTS update_farm//

CREATE PROCEDURE update_farm (
	IN id INT,
	IN name VARCHAR(255),
	IN location VARCHAR(255))

	BEGIN
		UPDATE farms
		SET name = name,
			location = location
		WHERE id = id;
	END//

-- DELIMITER ;
