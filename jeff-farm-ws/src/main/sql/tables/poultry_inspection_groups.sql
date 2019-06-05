-- DROP TABLE poultry_inspection_groups;
-- SELECT  * FROM poultry_inspection_groups LIMIT 0;

CREATE TABLE IF NOT EXISTS poultry_inspection_groups
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, notes VARCHAR(4095) NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	);

CREATE OR REPLACE FUNCTION poultry_inspection_group_update_modified_date_function()
RETURNS TRIGGER AS
$poultry_inspection_groups$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$poultry_inspection_groups$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inspection_group_modified_date_trigger
	ON poultry_inspection_groups;
CREATE TRIGGER poultry_inspection_group_modified_date_trigger
	BEFORE UPDATE
	ON poultry_inspection_groups
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inspection_group_update_modified_date_function();
