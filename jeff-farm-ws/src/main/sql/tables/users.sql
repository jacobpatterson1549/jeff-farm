CREATE TABLE IF NOT EXISTS users
	( id SERIAL PRIMARY KEY
	, user_name VARCHAR(20) UNIQUE NOT NULL
	, user_password CHAR(86) NOT NULL
	, first_name VARCHAR(255)
	, last_name VARCHAR(255)
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	);

CREATE OR REPLACE FUNCTION user_update_modified_date_function()
RETURNS TRIGGER AS
$users$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$users$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS user_modified_date_trigger
	ON users;
CREATE TRIGGER user_modified_date_trigger
	BEFORE UPDATE
	ON users
	FOR EACH ROW
	EXECUTE PROCEDURE user_update_modified_date_function();
