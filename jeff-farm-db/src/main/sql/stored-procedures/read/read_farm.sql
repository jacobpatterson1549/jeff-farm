--DELIMITER //

DROP PROCEDURE IF EXISTS read_farm//

CREATE PROCEDURE read_farm (
	IN id INT)

	BEGIN
		SELECT f.id, f.name, f.location
		FROM farms AS f
		WHERE f.id = id
			AND f.active = 1;
	END//

-- DELIMITER ;