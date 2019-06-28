CREATE TABLE IF NOT EXISTS livestock
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, name VARCHAR(255) NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	);

CREATE OR REPLACE FUNCTION livestock_update_modified_date_function()
RETURNS TRIGGER AS
$livestock$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$livestock$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_modified_date_trigger
	ON livestock;
CREATE TRIGGER livestock_modified_date_trigger
	BEFORE UPDATE
	ON livestock
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_update_modified_date_function();
