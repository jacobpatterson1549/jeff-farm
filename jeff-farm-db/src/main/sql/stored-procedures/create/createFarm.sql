-- DELIMITER //

DROP PROCEDURE IF EXISTS createFarm//

CREATE PROCEDURE createFarm (
	IN farmName VARCHAR(255),
	IN farmLocation VARCHAR(255),
	OUT farmID INT)

	BEGIN
		INSERT INTO farms (name, location)
		VALUES (farmName, farmLocation);

		SET farmID = LAST_INSERT_ID();
	END//

-- DELIMITER ;
