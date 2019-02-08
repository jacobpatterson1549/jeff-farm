-- DELIMITER //

DROP PROCEDURE IF EXISTS updateFarm//

CREATE PROCEDURE updateFarm (
	IN farmID INT,
	IN farmName VARCHAR(255),
	IN farmLocation VARCHAR(255))

	BEGIN
		UPDATE FARMS
		SET name = farmName,
			location = farmLocation
		WHERE ID = farmID;
	END//

-- DELIMITER ;
