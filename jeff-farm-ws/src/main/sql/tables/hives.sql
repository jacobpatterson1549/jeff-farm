-- DROP TABLE hives;
-- SELECT  * FROM hives LIMIT 0;

CREATE TABLE IF NOT EXISTS hives
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, name VARCHAR(255) NOT NULL
	, queen_color INT NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	);

CREATE OR REPLACE FUNCTION hive_update_modified_date_function()
RETURNS TRIGGER AS
$hives$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$hives$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_modified_date_trigger
	ON hives;
CREATE TRIGGER hive_modified_date_trigger
	AFTER UPDATE
	ON hives
	FOR EACH ROW
	EXECUTE PROCEDURE hive_update_modified_date_function();
