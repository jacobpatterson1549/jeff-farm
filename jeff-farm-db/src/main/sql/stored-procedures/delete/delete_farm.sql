-- DELIMITER $$

DROP PROCEDURE IF EXISTS delete_farm$$

CREATE PROCEDURE delete_farm (
	IN id INT)

	BEGIN
		DELETE f
		FROM farms AS f
		WHERE f.id = id;
	END$$

-- DELIMITER ;
