-- DELIMITER //

DROP PROCEDURE IF EXISTS delete_queen_bee//

CREATE PROCEDURE delete_queen_bee (
	IN id INT)

	BEGIN
		UPDATE queen_bees AS qb
		SET qb.active = 0
		WHERE qb.id = id
			AND qb.active = 1;
	END//

-- DELIMITER ;
