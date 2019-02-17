-- DELIMITER //

DROP PROCEDURE IF EXISTS delete_farm//

CREATE PROCEDURE delete_farm (
	IN id INT)

	BEGIN
		UPDATE farms
		SET active = 0
		WHERE id = id;
	END//

-- DELIMITER ;
