--DELIMITER $$

-- DROP TABLE hives_audit$$
-- SELECT  * FROM hives_audit LIMIT 0$$

CREATE TABLE IF NOT EXISTS hives_audit
(
	audit_id INT PRIMARY KEY AUTO_INCREMENT,
	action_type CHAR(1) NOT NULL, -- i (insert), b (before update), a (after update), d (delete)
	user_id INT NOT NULL,
	action_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	id INT,
	farm_id INT,
	name VARCHAR(255),
	queen_color BIT(24)
)$$

DROP PROCEDURE IF EXISTS hive_changed_function$$
CREATE PROCEDURE hive_changed_function (
	IN action_type CHAR(1),
	IN id INT,
	IN farm_id INT,
	IN name VARCHAR(255),
	IN queen_color BIT(24))

	BEGIN
		INSERT INTO hives_audit (
			action_type,
			user_id,
			id,
			farm_id,
			name,
			queen_color)
		VALUES (
			action_type,
			@user_id,
			id,
			farm_id,
			name,
			queen_color);
	END$$

DROP TRIGGER IF EXISTS hive_inserted_trigger$$
CREATE TRIGGER hive_inserted_trigger
	AFTER INSERT
	ON hives
	FOR EACH ROW
	BEGIN
		CALL hive_changed_function(
			'i',
			NEW.id,
			NEW.farm_id,
			NEW.name,
			NEW.queen_color);
	END$$

DROP TRIGGER IF EXISTS hive_updated_trigger$$
CREATE TRIGGER hive_updated_trigger
	AFTER UPDATE
	ON hives
	FOR EACH ROW
	BEGIN
		CALL hive_changed_function(
			'b',
			OLD.id,
			OLD.farm_id,
			OLD.name,
			OLD.queen_color);
		CALL hive_changed_function(
			'a',
			NEW.id,
			NEW.farm_id,
			NEW.name,
			NEW.queen_color);
	END$$

DROP TRIGGER IF EXISTS hive_deleted_trigger$$
CREATE TRIGGER hive_deleted_trigger
	AFTER DELETE
	ON hives
	FOR EACH ROW
	BEGIN
		CALL hive_changed_function(
			'd',
			OLD.id,
			OLD.farm_id,
			OLD.name,
			OLD.queen_color);
	END$$

-- DELIMITER ;