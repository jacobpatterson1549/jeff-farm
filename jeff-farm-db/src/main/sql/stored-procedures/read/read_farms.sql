--DELIMITER //

DROP PROCEDURE IF EXISTS read_farms//

CREATE PROCEDURE read_farms ()

	BEGIN
		SELECT f.id, f.name, f.location
		FROM f.farms
		WHERE f.active = 1;
	END//

-- DELIMITER ;
