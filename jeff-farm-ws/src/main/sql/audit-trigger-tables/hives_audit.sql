-- DROP TABLE hives_audit;
-- SELECT  * FROM hives_audit LIMIT 0;
-- 
CREATE TABLE IF NOT EXISTS hives_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, farm_id INT
	, name VARCHAR(255)
	, queen_color INT
	);


CREATE OR REPLACE FUNCTION hive_inserted_function()
RETURNS TRIGGER AS
$hives_audit$
	BEGIN
		INSERT INTO hives_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			, queen_color
			)
		VALUES
			( 'i'
			, CAST(current_setting('jeff_farm_db.user_id') AS INT)
			, NEW.id
			, NEW.farm_id
			, NEW. name
			, NEW.queen_color
			);
		RETURN NEW;
	END;
$hives_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inserted_trigger
	ON hives;
CREATE TRIGGER hive_inserted_trigger
	AFTER INSERT
	ON hives
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inserted_function();


CREATE OR REPLACE FUNCTION hive_updated_function()
RETURNS TRIGGER AS
$hives_audit$
	BEGIN
		INSERT INTO hives_audit
			( action_type
			, user_id
			, id
			, name
			, queen_color
			)
		VALUES
			( 'b'
			, CAST(current_setting('jeff_farm_db.user_id') AS INT)
			, OLD.id
			, OLD.name
			, OLD.queen_color
			);
		INSERT INTO hives_audit
			( action_type
			, user_id
			, id
			, name
			, queen_color)
		VALUES
			( 'a'
			, CAST(current_setting('jeff_farm_db.user_id') AS INT)
			, NEW.id
			, NEW.name
			, NEW.queen_color
			);
		RETURN NEW;
	END;
$hives_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_updated_trigger
	ON hives;
CREATE TRIGGER hive_updated_trigger
	AFTER UPDATE
	ON hives
	FOR EACH ROW
	EXECUTE PROCEDURE hive_updated_function();


CREATE OR REPLACE FUNCTION hive_deleted_function()
RETURNS TRIGGER AS
$hives_audit$
	BEGIN
		INSERT INTO hives_audit
			( action_type
			, user_id
			, id
			, name
			, queen_color
			)
		VALUES
			( 'd'
			, CAST(current_setting('jeff_farm_db.user_id') AS INT)
			, OLD.id
			, OLD.name
			, OLD.queen_color
			);
		RETURN NEW;
	END;
$hives_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_deleted_trigger
	ON hives;
CREATE TRIGGER hive_deleted_trigger
	AFTER DELETE
	ON hives
	FOR EACH ROW
	EXECUTE PROCEDURE hive_deleted_function();
