CREATE TABLE IF NOT EXISTS livestock_maps
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, target_id INT NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	, FOREIGN KEY (target_id)
		REFERENCES livestock(id)
	);

CREATE OR REPLACE FUNCTION livestock_inspection_group_update_modified_date_function()
RETURNS TRIGGER AS
$livestock_maps$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$livestock_maps$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_inspection_group_modified_date_trigger
	ON livestock_maps;
CREATE TRIGGER livestock_inspection_group_modified_date_trigger
	BEFORE UPDATE
	ON livestock_maps
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_inspection_group_update_modified_date_function();
