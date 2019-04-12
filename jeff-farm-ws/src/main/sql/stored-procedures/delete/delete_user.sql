-- DELIMITER $$

DROP PROCEDURE IF EXISTS delete_user$$

CREATE PROCEDURE delete_user (
	IN id INT)

	BEGIN
		DELETE ur
		FROM user_roles AS ur
		JOIN users AS u ON u.user_name = ur.user_name
		WHERE u.id = id;

		DELETE u
		FROM users AS u
		WHERE u.id = id;
END$$

-- DELIMITER ;
