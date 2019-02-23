--DELIMITER //

DROP PROCEDURE IF EXISTS read_hives//

CREATE PROCEDURE read_hives (
	IN farm_id INT)

	BEGIN
		SELECT h.id, h.name
		FROM hives AS h
		WHERE h.active = 1
			AND h.farm_id = farm_id;
	END//

-- DELIMITER ;
