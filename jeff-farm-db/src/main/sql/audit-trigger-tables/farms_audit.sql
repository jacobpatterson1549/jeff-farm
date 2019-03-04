--DELIMITER $$

-- DROP TABLE farms_audit;
-- SELECT  * FROM farms_audit LIMIT 0;

CREATE TABLE IF NOT EXISTS farms_audit
(
	audit_id INT PRIMARY KEY AUTO_INCREMENT,
	action_type CHAR(1) NOT NULL, -- i (insert), b (before update), a (after update), d (delete)
	-- userId INT, -- TODO: track which user made the change
	action_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	id INT,
	name VARCHAR(255),
	location VARCHAR(255)
)$$

DROP PROCEDURE IF EXISTS farm_changed_function$$
CREATE PROCEDURE farm_changed_function (
	IN action_type CHAR(1),
	IN id INT,
	IN name VARCHAR(255),
	IN location VARCHAR(255))

	BEGIN
		INSERT INTO farms_audit (action_type, id, name, location)
		VALUES (action_type, id, name, location);
	END$$

DROP TRIGGER IF EXISTS farm_inserted_trigger$$
CREATE TRIGGER farm_inserted_trigger
	AFTER INSERT
	ON farms
	FOR EACH ROW
	BEGIN
		CALL farm_changed_function(
			'i',
			NEW.id,
			NEW.name,
			NEW.location);
	END$$

DROP TRIGGER IF EXISTS farm_updated_trigger$$
CREATE TRIGGER farm_updated_trigger
	AFTER UPDATE
	ON farms
	FOR EACH ROW
	BEGIN
		CALL farm_changed_function(
			'b',
			OLD.id,
			OLD.name,
			OLD.location);
		CALL farm_changed_function(
			'a',
			NEW.id,
			NEW.name,
			NEW.location);
	END$$

DROP TRIGGER IF EXISTS farm_deleted_trigger$$
CREATE TRIGGER farm_deleted_trigger
	AFTER DELETE
	ON farms
	FOR EACH ROW
	BEGIN
		CALL farm_changed_function(
			'd',
			OLD.id,
			OLD.name,
			OLD.location);
	END$$

-- DELIMITER ;