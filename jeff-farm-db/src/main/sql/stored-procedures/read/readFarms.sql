--DELIMITER //

DROP PROCEDURE IF EXISTS readFarms//

CREATE PROCEDURE readFarms ()

	BEGIN
		SELECT ID, name, location
		FROM farms
		WHERE active = 1;
	END//

-- DELIMITER ;
