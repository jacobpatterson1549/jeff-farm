--DELIMITER $$

DROP PROCEDURE IF EXISTS read_hives$$

CREATE PROCEDURE read_hives (
	IN farm_id INT)

	BEGIN
		SELECT h.id
			, h.farm_id
			, h.name
			, h.queen_color
			, h.created_date
			, h.modified_date
		FROM hives AS h
		WHERE h.farm_id = farm_id;
	END$$

-- DELIMITER ;
