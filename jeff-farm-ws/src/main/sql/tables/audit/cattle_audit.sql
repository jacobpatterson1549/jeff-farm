CREATE TABLE IF NOT EXISTS cattle_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, farm_id INT
	, name VARCHAR(255)
	);


CREATE OR REPLACE FUNCTION cattle_inserted_function()
RETURNS TRIGGER AS
$cattle_audit$
	BEGIN
		INSERT INTO cattle_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			)
		VALUES
			( 'i'
			, get_user_id()
			, NEW.id
			, NEW.farm_id
			, NEW.name
			);
		RETURN NEW;
	END;
$cattle_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_inserted_trigger
	ON cattle;
CREATE TRIGGER cattle_inserted_trigger
	AFTER INSERT
	ON cattle
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_inserted_function();


CREATE OR REPLACE FUNCTION cattle_updated_function()
RETURNS TRIGGER AS
$cattle_audit$
	BEGIN
		INSERT INTO cattle_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			)
		VALUES
			( 'b'
			, get_user_id()
			, OLD.id
			, OLD.farm_id
			, OLD.name
			);
		INSERT INTO cattle_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			)
		VALUES
			( 'a'
			, get_user_id()
			, NEW.id
			, NEW.farm_id
			, NEW.name
			);
		RETURN NEW;
	END;
$cattle_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_updated_trigger
	ON cattle;
CREATE TRIGGER cattle_updated_trigger
	AFTER UPDATE
	ON cattle
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_updated_function();


CREATE OR REPLACE FUNCTION cattle_deleted_function()
RETURNS TRIGGER AS
$cattle_audit$
	BEGIN
		INSERT INTO cattle_audit
			( action_type
			, user_id
			, id
			, farm_id
			, name
			)
		VALUES
			( 'd'
			, get_user_id()
			, OLD.id
			, OLD.farm_id
			, OLD.name
			);
		RETURN NEW;
	END;
$cattle_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_deleted_trigger
	ON cattle;
CREATE TRIGGER cattle_deleted_trigger
	AFTER DELETE
	ON cattle
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_deleted_function();
