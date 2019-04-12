-- DROP TABLE farms;
-- SELECT  * FROM farms LIMIT 0;

CREATE TABLE IF NOT EXISTS farms
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	location VARCHAR(255),
	created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION farm_update_modified_date_function()
RETURNS TRIGGER AS
$farms$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$farms$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS farm_modified_date_trigger
	ON farms;
CREATE TRIGGER farm_modified_date_trigger
	AFTER UPDATE
	ON farms
	FOR EACH ROW
	EXECUTE PROCEDURE farm_update_modified_date_function();