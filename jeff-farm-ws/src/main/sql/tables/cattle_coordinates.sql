CREATE TABLE IF NOT EXISTS cattle_coordinates
	( id SERIAL PRIMARY KEY
	, group_id INT NOT NULL
	, latitude INT NOT NULL
	, longitude INT NOT NULL
	, display_order INT NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (group_id)
		REFERENCES cattle_maps(id)
	);

CREATE OR REPLACE FUNCTION cattle_coordinate_update_modified_date_function()
RETURNS TRIGGER AS
$cattle_coordinates$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$cattle_coordinates$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_coordinate_modified_date_trigger
	ON cattle_coordinates;
CREATE TRIGGER cattle_coordinate_modified_date_trigger
	BEFORE UPDATE
	ON cattle_coordinates
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_coordinate_update_modified_date_function();
