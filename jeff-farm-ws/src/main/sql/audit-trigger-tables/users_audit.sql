--DELIMITER $$

-- DROP TABLE users_audit$$
-- SELECT  * FROM users_audit LIMIT 0$$

CREATE TABLE IF NOT EXISTS users_audit
(
	audit_id INT PRIMARY KEY AUTO_INCREMENT,
	action_type CHAR(1) NOT NULL, -- i (insert), b (before update), a (after update), d (delete)
	-- userId INT, -- TODO: track which user made the change
	action_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	id INT,
	user_name VARCHAR(20),
	user_password CHAR(86),
	first_name VARCHAR(255),
	last_name VARCHAR(255)
)$$

DROP PROCEDURE IF EXISTS user_changed_function$$
CREATE PROCEDURE user_changed_function (
	IN action_type CHAR(1),
	IN id INT,
	IN user_name VARCHAR(20),
	IN user_password CHAR(86),
	IN first_name VARCHAR(255),
	IN last_name VARCHAR(255))

	BEGIN
		INSERT INTO users_audit (
			action_type,
			id,
			user_name,
			user_password,
			first_name,
			last_name)
		VALUES (
			action_type,
			id,
			user_name,
			user_password,
			first_name,
			last_name);
	END$$

DROP TRIGGER IF EXISTS user_inserted_trigger$$
CREATE TRIGGER user_inserted_trigger
	AFTER INSERT
	ON users
	FOR EACH ROW
	BEGIN
		CALL user_changed_function(
			'i',
			NEW.id,
			NEW.user_name,
			NEW.user_password,
			NEW.first_name,
			NEW.last_name);
	END$$

DROP TRIGGER IF EXISTS user_updated_trigger$$
CREATE TRIGGER user_updated_trigger
	AFTER UPDATE
	ON users
	FOR EACH ROW
	BEGIN
		CALL user_changed_function(
			'b',
			OLD.id,
			OLD.user_name,
			OLD.user_password,
			OLD.first_name,
			OLD.last_name);
		CALL user_changed_function(
			'a',
			NEW.id,
			NEW.user_name,
			NEW.user_password,
			NEW.first_name,
			NEW.last_name);
	END$$

DROP TRIGGER IF EXISTS user_deleted_trigger$$
CREATE TRIGGER user_deleted_trigger
	AFTER DELETE
	ON users
	FOR EACH ROW
	BEGIN
		CALL user_changed_function(
			'd',
			OLD.user_name,
			OLD.user_password,
			OLD.first_name,
			OLD.last_name);
	END$$

-- DELIMITER ;