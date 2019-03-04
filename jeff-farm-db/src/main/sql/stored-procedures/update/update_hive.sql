-- DELIMITER $$

DROP PROCEDURE IF EXISTS update_hive$$

CREATE PROCEDURE update_hive (
	IN id INT,
	IN name VARCHAR(255),
	IN queen_color BIT(24))

	BEGIN
		UPDATE hives AS h
		SET h.name = name
			, h.queen_color = queen_color
		WHERE h.id = id;
	END$$

-- DELIMITER ;
