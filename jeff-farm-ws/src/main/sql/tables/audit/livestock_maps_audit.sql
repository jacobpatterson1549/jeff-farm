CREATE TABLE IF NOT EXISTS livestock_maps_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, target_id INT NOT NULL
	);


CREATE OR REPLACE FUNCTION livestock_map_inserted_function()
RETURNS TRIGGER AS
$livestock_maps_audit$
	BEGIN
		INSERT INTO livestock_maps_audit
			( action_type
			, user_id
			, id
			, target_id
			)
		VALUES
			( 'i'
			, get_user_id()
			, NEW.id
			, NEW.target_id
			);
		RETURN NEW;
	END;
$livestock_maps_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_map_inserted_trigger
	ON livestock_maps;
CREATE TRIGGER livestock_map_inserted_trigger
	AFTER INSERT
	ON livestock_maps
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_map_inserted_function();


CREATE OR REPLACE FUNCTION livestock_map_updated_function()
RETURNS TRIGGER AS
$livestock_maps_audit$
	BEGIN
		INSERT INTO livestock_maps_audit
			( action_type
			, user_id
			, id
			, target_id
			)
		VALUES
			( 'b'
			, get_user_id()
			, OLD.id
			, OLD.target_id
			);
		INSERT INTO livestock_maps_audit
			( action_type
			, user_id
			, id
			, target_id
			)
		VALUES
			( 'a'
			, get_user_id()
			, NEW.id
			, NEW.target_id
			);
		RETURN NEW;
	END;
$livestock_maps_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_map_updated_trigger
	ON livestock_maps;
CREATE TRIGGER livestock_map_updated_trigger
	AFTER UPDATE
	ON livestock_maps
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_map_updated_function();


CREATE OR REPLACE FUNCTION livestock_map_deleted_function()
RETURNS TRIGGER AS
$livestock_maps_audit$
	BEGIN
		INSERT INTO livestock_maps_audit
			( action_type
			, user_id
			, id
			, target_id
			)
		VALUES
			( 'd'
			, get_user_id()
			, OLD.id
			, OLD.target_id
			);
		RETURN NEW;
	END;
$livestock_maps_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_map_deleted_trigger
	ON livestock_maps;
CREATE TRIGGER livestock_map_deleted_trigger
	AFTER DELETE
	ON livestock_maps
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_map_deleted_function();
