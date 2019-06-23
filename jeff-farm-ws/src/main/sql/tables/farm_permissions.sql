CREATE TABLE IF NOT EXISTS farm_permissions
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, user_id INT NOT NULL
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, UNIQUE (farm_id, user_id)
	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	, FOREIGN KEY (user_id)
		REFERENCES users (id)
	);

CREATE OR REPLACE FUNCTION farm_permission_update_modified_date_function()
RETURNS TRIGGER AS
$farms$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$farms$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS farm_permission_modified_date_trigger
	ON farms;
CREATE TRIGGER farm_permission_modified_date_trigger
	BEFORE UPDATE
	ON farms
	FOR EACH ROW
	EXECUTE PROCEDURE farm_permission_update_modified_date_function();

