CREATE TABLE IF NOT EXISTS livestock_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, farm_id INT
	, name VARCHAR(255)
	);


CREATE OR REPLACE FUNCTION livestock_inserted_function()
RETURNS TRIGGER AS
$livestock_audit$
	BEGIN
		INSERT INTO livestock_audit
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
$livestock_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_inserted_trigger
	ON livestock;
CREATE TRIGGER livestock_inserted_trigger
	AFTER INSERT
	ON livestock
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_inserted_function();


CREATE OR REPLACE FUNCTION livestock_updated_function()
RETURNS TRIGGER AS
$livestock_audit$
	BEGIN
		INSERT INTO livestock_audit
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
		INSERT INTO livestock_audit
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
$livestock_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_updated_trigger
	ON livestock;
CREATE TRIGGER livestock_updated_trigger
	AFTER UPDATE
	ON livestock
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_updated_function();


CREATE OR REPLACE FUNCTION livestock_deleted_function()
RETURNS TRIGGER AS
$livestock_audit$
	BEGIN
		INSERT INTO livestock_audit
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
$livestock_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_deleted_trigger
	ON livestock;
CREATE TRIGGER livestock_deleted_trigger
	AFTER DELETE
	ON livestock
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_deleted_function();
