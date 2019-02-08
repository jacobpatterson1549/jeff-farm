--DELIMITER //

DROP PROCEDURE IF EXISTS readFarms//

CREATE PROCEDURE readFarms (
	OUT farmID INT,
	IN farmName VARCHAR(255),
	IN farmLocation VARCHAR(255))

	BEGIN
		SELECT ID, name, farmLocation
		FROM farms
		WHERE active = 1;
	END//

-- DELIMITER ;
