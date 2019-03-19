-- DELIMITER $$

DROP PROCEDURE IF EXISTS create_user$$

CREATE PROCEDURE create_user (
	IN user_name VARCHAR(20),
	IN first_name VARCHAR(255),
	IN last_name VARCHAR(255),
	OUT id INT)

	BEGIN
		INSERT INTO users (user_name, first_name, last_name)
		VALUES (user_name, first_name, last_name);

		SET id = LAST_INSERT_ID();

		INSERT INTO user_roles (user_name, user_role)
		VALUES (user_name, 'user');
	END$$

-- DELIMITER ;
