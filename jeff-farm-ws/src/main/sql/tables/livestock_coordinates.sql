CREATE TABLE IF NOT EXISTS livestock_coordinates
	( id SERIAL PRIMARY KEY
	, group_id INT NOT NULL
	, latitude DOUBLE PRECISION NOT NULL
	, longitude DOUBLE PRECISION NOT NULL
	, display_order INT NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (group_id)
		REFERENCES livestock_maps(id)
	);

CREATE OR REPLACE FUNCTION livestock_coordinate_update_modified_date_function()
RETURNS TRIGGER AS
$livestock_coordinates$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$livestock_coordinates$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_coordinate_modified_date_trigger
	ON livestock_coordinates;
CREATE TRIGGER livestock_coordinate_modified_date_trigger
	BEFORE UPDATE
	ON livestock_coordinates
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_coordinate_update_modified_date_function();
