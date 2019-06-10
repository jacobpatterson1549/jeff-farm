-- DROP TABLE poultry_inspections;
-- SELECT  * FROM poultry_inspections LIMIT 0;

CREATE TABLE IF NOT EXISTS poultry_inspections
	( id SERIAL PRIMARY KEY
	, group_id INT NOT NULL
	, target_id INT NOT NULL
	, bird_count INT NOT NULL
	, egg_count INT NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (group_id)
		REFERENCES poultry_inspection_groups(id)
	, FOREIGN KEY (poultry_id)
		REFERENCES poultry(id)
	);

CREATE OR REPLACE FUNCTION poultry_inspection_update_modified_date_function()
RETURNS TRIGGER AS
$poultry_inspections$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$poultry_inspections$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inspection_modified_date_trigger
	ON poultry_inspections;
CREATE TRIGGER poultry_inspection_modified_date_trigger
	BEFORE UPDATE
	ON poultry_inspections
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inspection_update_modified_date_function();
