CREATE TABLE IF NOT EXISTS poultry
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, name VARCHAR(255) NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	);

CREATE OR REPLACE FUNCTION poultry_update_modified_date_function()
RETURNS TRIGGER AS
$poultry$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$poultry$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_modified_date_trigger
	ON poultry;
CREATE TRIGGER poultry_modified_date_trigger
	BEFORE UPDATE
	ON poultry
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_update_modified_date_function();
