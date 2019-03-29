-- DELIMITER $$

DROP PROCEDURE IF EXISTS update_user$$

CREATE PROCEDURE update_user (
	IN id INT,
	IN first_name VARCHAR(255),
	IN last_name VARCHAR(255))

	BEGIN
		UPDATE users AS u
		SET u.first_name = first_name,
			u.last_name = last_name
		WHERE u.id = id;
	END$$

-- DELIMITER ;
