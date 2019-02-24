--DELIMITER //

DROP PROCEDURE IF EXISTS read_hives//

CREATE PROCEDURE read_hives (
	IN farm_id INT)

	BEGIN
		SELECT h.id, h.name
		FROM hives AS h
		WHERE h.farm_id = farm_id
			AND h.active = 1;
	END//

-- DELIMITER ;
