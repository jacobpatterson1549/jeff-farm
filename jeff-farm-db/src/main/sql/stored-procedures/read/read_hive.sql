--DELIMITER //

DROP PROCEDURE IF EXISTS read_hive//

CREATE PROCEDURE read_hive (
	IN id INT)

	BEGIN
		SELECT h.id
			, h.farm_id
			, h.name
			, h.queen_color
			, h.created_date
			, h.modified_date
		FROM hives AS h
		WHERE h.id = id
			AND h.active = 1;
	END//

-- DELIMITER ;
