CREATE TABLE IF NOT EXISTS cattle_maps
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, target_id INT NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	, FOREIGN KEY (target_id)
		REFERENCES cattle(id)
	);

CREATE OR REPLACE FUNCTION cattle_inspection_group_update_modified_date_function()
RETURNS TRIGGER AS
$cattle_maps$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$cattle_maps$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_inspection_group_modified_date_trigger
	ON cattle_maps;
CREATE TRIGGER cattle_inspection_group_modified_date_trigger
	BEFORE UPDATE
	ON cattle_maps
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_inspection_group_update_modified_date_function();
