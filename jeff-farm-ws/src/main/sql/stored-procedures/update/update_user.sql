-- DELIMITER $$

DROP PROCEDURE IF EXISTS update_user$$

CREATE PROCEDURE update_user (
	IN user_name VARCHAR(20),
	IN first_name VARCHAR(255),
	IN last_name VARCHAR(255))

	BEGIN
		UPDATE users AS u
		SET u.first_name = first_name,
			u.last_name = last_name
		WHERE u.user_name = user_name;
	END$$

-- DELIMITER ;
