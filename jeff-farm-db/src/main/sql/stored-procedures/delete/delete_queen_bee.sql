-- DELIMITER //

DROP PROCEDURE IF EXISTS delete_farm//

CREATE PROCEDURE delete_farm (
	IN id INT,
	IN hive_id INT,
	IN farm_id INT)

	BEGIN
		UPDATE queen_bees AS qb
		JOIN hives AS h ON h.id = hi.hive_id
		SET qb.active = 0
		WHERE qb.id = id
			AND h.id = hive_id
			AND h.farm_id = farm_id;
	END//

-- DELIMITER ;
