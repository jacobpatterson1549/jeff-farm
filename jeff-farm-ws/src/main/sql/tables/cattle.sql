CREATE TABLE IF NOT EXISTS cattle
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, name VARCHAR(255) NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	);

CREATE OR REPLACE FUNCTION cattle_update_modified_date_function()
RETURNS TRIGGER AS
$cattle$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$cattle$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_modified_date_trigger
	ON cattle;
CREATE TRIGGER cattle_modified_date_trigger
	BEFORE UPDATE
	ON cattle
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_update_modified_date_function();
