--DELIMITER //

DROP PROCEDURE IF EXISTS read_queen_bees//

CREATE PROCEDURE read_queen_bees (
	IN hive_id INT,
	IN farm_id INT)

	BEGIN
		SELECT qb.id, qb.hive_id, qb.mark_color
		FROM queen_bees AS qb
		JOIN hives AS h ON h.id = qb.hive_id
		WHERE qb.active = 1
			AND h.id = hive_id
			AND h.farm_id = farm_id;
	END//

-- DELIMITER ;
