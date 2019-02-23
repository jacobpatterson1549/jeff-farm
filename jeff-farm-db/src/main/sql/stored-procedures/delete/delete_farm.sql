-- DELIMITER //

DROP PROCEDURE IF EXISTS delete_farm//

CREATE PROCEDURE delete_farm (
	IN id INT)

	BEGIN
		UPDATE farms AS f
		SET f.active = 0
		WHERE f.id = id;
	END//

-- DELIMITER ;
