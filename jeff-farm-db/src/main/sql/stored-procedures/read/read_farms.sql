--DELIMITER //

DROP PROCEDURE IF EXISTS read_farms//

CREATE PROCEDURE read_farms ()

	BEGIN
		SELECT id, name, location
		FROM farms
		WHERE active = 1;
	END//

-- DELIMITER ;
