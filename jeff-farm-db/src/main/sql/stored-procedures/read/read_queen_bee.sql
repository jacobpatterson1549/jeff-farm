--DELIMITER //

DROP PROCEDURE IF EXISTS read_queen_bee//

CREATE PROCEDURE read_queen_bee (
	IN id INT)

	BEGIN
		SELECT qb.id, qb.hive_id, qb.mark_color
		FROM queen_bees AS qb
		WHERE qb.id = id
			AND qb.active = 1;
	END//

-- DELIMITER ;
