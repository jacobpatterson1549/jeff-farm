-- DELIMITER $$

DROP PROCEDURE IF EXISTS can_delete_farm$$

CREATE PROCEDURE can_delete_farm (
	IN id INT,
	OUT can_delete BIT(1))

	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN 1 ELSE 0 END INTO can_delete
		FROM hives AS h
		WHERE h.farm_id = id
			AND h.active = 1;
	END$$

-- DELIMITER ;
