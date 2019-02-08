-- DELIMITER //

DROP PROCEDURE IF EXISTS deleteFarm//

CREATE PROCEDURE deleteFarm (
	IN farmID INT)

	BEGIN
		UPDATE farms
		SET active = 0
		WHERE ID = farmID;
	END//

-- DELIMITER ;
