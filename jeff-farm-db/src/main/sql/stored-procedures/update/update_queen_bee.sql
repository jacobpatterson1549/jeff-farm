-- DELIMITER //

DROP PROCEDURE IF EXISTS update_queen_bee//

CREATE PROCEDURE update_queen_bee (
	IN id INT,
	IN hive_id INT,
	IN mark_color VARCHAR(255))

	BEGIN
		UPDATE queen_bees AS qb
		JOIN hives AS h ON h.id = hi.hive_id
		SET qb.mark_color = mark_color
		WHERE qb.id = id
			AND qb.active = 1
			AND h.id = hive_id;
	END//

-- DELIMITER ;
