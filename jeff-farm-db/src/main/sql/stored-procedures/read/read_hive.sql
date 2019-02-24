--DELIMITER //

DROP PROCEDURE IF EXISTS read_hive//

CREATE PROCEDURE read_hive (
	IN id INT)

	BEGIN
		SELECT h.id, h.name
		FROM hives AS h
		WHERE h.id = id
			AND h.active = 1;
	END//

-- DELIMITER ;
