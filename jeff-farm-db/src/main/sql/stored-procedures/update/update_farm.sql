-- DELIMITER $$

DROP PROCEDURE IF EXISTS update_farm$$

CREATE PROCEDURE update_farm (
	IN id INT,
	IN name VARCHAR(255),
	IN location VARCHAR(255))

	BEGIN
-- TODO: only make updates if name/location changed! (same for hives, hiveInspections)
		UPDATE farms AS f
		SET f.name = name,
			f.location = location
		WHERE f.id = id;
	END$$

-- DELIMITER ;
