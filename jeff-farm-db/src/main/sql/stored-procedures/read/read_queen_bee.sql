--DELIMITER //

DROP PROCEDURE IF EXISTS read_queen_bee//

CREATE PROCEDURE read_queen_bee (
	IN id INT)

	BEGIN
		SELECT qb.id, qb.hive_id, qb.mark_color, qb.created_date, qb.modified_date
		FROM queen_bees AS qb
		WHERE qb.id = id
			AND qb.active = 1;
	END//

-- DELIMITER ;
