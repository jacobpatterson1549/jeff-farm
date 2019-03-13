--DELIMITER $$

DROP PROCEDURE IF EXISTS read_farm$$

CREATE PROCEDURE read_farm (
	IN id INT)

	BEGIN
		SELECT f.id, f.name, f.location, f.created_date, f.modified_date
		FROM farms AS f
		WHERE f.id = id;
	END$$

-- DELIMITER ;
