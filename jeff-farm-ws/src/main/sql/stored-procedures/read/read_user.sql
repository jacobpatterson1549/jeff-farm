--DELIMITER $$

DROP PROCEDURE IF EXISTS read_user$$

CREATE PROCEDURE read_user (
	IN user_name VARCHAR(20))

	BEGIN
		SELECT u.id, u.user_name, u.first_name, u.last_name
		FROM users AS u
		WHERE u.user_name = user_name;
	END$$

-- DELIMITER ;
