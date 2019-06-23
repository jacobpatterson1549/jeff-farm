CREATE TABLE IF NOT EXISTS hive_inspection_groups
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, notes VARCHAR(4095) NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	);

CREATE OR REPLACE FUNCTION hive_inspection_group_update_modified_date_function()
RETURNS TRIGGER AS
$hive_inspection_groups$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$hive_inspection_groups$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inspection_group_modified_date_trigger
	ON hive_inspection_groups;
CREATE TRIGGER hive_inspection_group_modified_date_trigger
	BEFORE UPDATE
	ON hive_inspection_groups
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inspection_group_update_modified_date_function();
