-- DROP TABLE poultry_audit;
-- SELECT * FROM poultry_audit LIMIT 0;
-- 
CREATE TABLE IF NOT EXISTS poultry_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, farm_id INT
	, name VARCHAR(255)
	);


CREATE OR REPLACE FUNCTION poultry_inserted_function()
RETURNS TRIGGER AS
$poultry_audit$
	BEGIN
		INSERT INTO poultry_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			)
		VALUES
			( 'i'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, NEW.id
			, NEW.farm_id
			, NEW.name
			);
		RETURN NEW;
	END;
$poultry_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inserted_trigger
	ON poultry;
CREATE TRIGGER poultry_inserted_trigger
	AFTER INSERT
	ON poultry
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inserted_function();


CREATE OR REPLACE FUNCTION poultry_updated_function()
RETURNS TRIGGER AS
$poultry_audit$
	BEGIN
		INSERT INTO poultry_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			)
		VALUES
			( 'b'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, OLD.id
			, OLD.farm_id
			, OLD.name
			);
		INSERT INTO poultry_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			)
		VALUES
			( 'a'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, NEW.id
			, NEW.farm_id
			, NEW.name
			);
		RETURN NEW;
	END;
$poultry_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_updated_trigger
	ON poultry;
CREATE TRIGGER poultry_updated_trigger
	AFTER UPDATE
	ON poultry
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_updated_function();


CREATE OR REPLACE FUNCTION poultry_deleted_function()
RETURNS TRIGGER AS
$poultry_audit$
	BEGIN
		INSERT INTO poultry_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			)
		VALUES
			( 'd'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, OLD.id
			, OLD.farm_id
			, OLD.name
			);
		RETURN NEW;
	END;
$poultry_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_deleted_trigger
	ON poultry;
CREATE TRIGGER poultry_deleted_trigger
	AFTER DELETE
	ON poultry
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_deleted_function();
