-- DROP TABLE farms_audit;
-- SELECT * FROM farms_audit LIMIT 0;
-- 
CREATE TABLE IF NOT EXISTS farms_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, name VARCHAR(255)
	, location VARCHAR(255)
	);


CREATE OR REPLACE FUNCTION farm_inserted_function()
RETURNS TRIGGER AS
$farms_audit$
	BEGIN
		INSERT INTO farms_audit
			( action_type
			, user_id
			, id
			, name
			, location
			)
		VALUES
			( 'i'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, NEW.id
			, NEW.name
			, NEW.location
			);
		RETURN NEW;
	END;
$farms_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS farm_inserted_trigger
	ON farms;
CREATE TRIGGER farm_inserted_trigger
	AFTER INSERT
	ON farms
	FOR EACH ROW
	EXECUTE PROCEDURE farm_inserted_function();


CREATE OR REPLACE FUNCTION farm_updated_function()
RETURNS TRIGGER AS
$farms_audit$
	BEGIN
		INSERT INTO farms_audit
			( action_type
			, user_id
			, id
			, name
			, location
			)
		VALUES
			( 'b'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, OLD.id
			, OLD.name
			, OLD.location
			);
		INSERT INTO farms_audit
			( action_type
			, user_id
			, id
			, name
			, location
			)
		VALUES
			( 'a'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, NEW.id
			, NEW.name
			, NEW.location
			);
		RETURN NEW;
	END;
$farms_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS farm_updated_trigger
	ON farms;
CREATE TRIGGER farm_updated_trigger
	AFTER UPDATE
	ON farms
	FOR EACH ROW
	EXECUTE PROCEDURE farm_updated_function();


CREATE OR REPLACE FUNCTION farm_deleted_function()
RETURNS TRIGGER AS
$farms_audit$
	BEGIN
		INSERT INTO farms_audit
			( action_type
			, user_id
			, id
			, name
			, location
			)
		VALUES
			( 'd'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, OLD.id
			, OLD.name
			, OLD.location
			);
		RETURN NEW;
	END;
$farms_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS farm_deleted_trigger
	ON farms;
CREATE TRIGGER farm_deleted_trigger
	AFTER DELETE
	ON farms
	FOR EACH ROW
	EXECUTE PROCEDURE farm_deleted_function();
