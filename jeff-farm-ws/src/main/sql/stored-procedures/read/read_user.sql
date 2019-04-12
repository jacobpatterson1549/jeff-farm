--DELIMITER $$

DROP PROCEDURE IF EXISTS read_user$$

CREATE PROCEDURE read_user (
	IN id INT)

	BEGIN
		SELECT u.id
			, u.user_name
			, u.first_name
			, u.last_name
			, u.created_date
			, u.modified_date
		FROM users AS u
		WHERE u.id = id;
	END$$

-- DELIMITER ;
