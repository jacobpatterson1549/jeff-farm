-- DELIMITER //

DROP PROCEDURE IF EXISTS create_queen_bee//

CREATE PROCEDURE create_queen_bee (
	IN hive_id INT,
	IN mark_color VARCHAR(255),
	OUT id INT)

	BEGIN
		INSERT INTO queen_bees (hive_id, mark_color)
		SELECT hive_id, mark_color
		FROM hives AS h
		WHERE h.id = hive_id
			AND h.active = 1;

		SET id = LAST_INSERT_ID();
	END//

-- DELIMITER ;
