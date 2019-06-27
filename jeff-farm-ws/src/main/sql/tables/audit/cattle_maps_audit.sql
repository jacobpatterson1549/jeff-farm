CREATE TABLE IF NOT EXISTS cattle_maps_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, group_id INT NOT NULL
	, target_id INT NOT NULL
	);


CREATE OR REPLACE FUNCTION cattle_map_inserted_function()
RETURNS TRIGGER AS
$cattle_maps_audit$
	BEGIN
		INSERT INTO cattle_maps_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			)
		VALUES
			( 'i'
			, get_user_id()
			, NEW.id
			, NEW.group_id
			, NEW.target_id
			);
		RETURN NEW;
	END;
$cattle_maps_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_map_inserted_trigger
	ON cattle_maps;
CREATE TRIGGER cattle_map_inserted_trigger
	AFTER INSERT
	ON cattle_maps
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_map_inserted_function();


CREATE OR REPLACE FUNCTION cattle_map_updated_function()
RETURNS TRIGGER AS
$cattle_maps_audit$
	BEGIN
		INSERT INTO cattle_maps_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			)
		VALUES
			( 'b'
			, get_user_id()
			, OLD.id
			, OLD.group_id
			, OLD.target_id
			);
		INSERT INTO cattle_maps_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			)
		VALUES
			( 'a'
			, get_user_id()
			, NEW.id
			, NEW.group_id
			, NEW.target_id
			);
		RETURN NEW;
	END;
$cattle_maps_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_map_updated_trigger
	ON cattle_maps;
CREATE TRIGGER cattle_map_updated_trigger
	AFTER UPDATE
	ON cattle_maps
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_map_updated_function();


CREATE OR REPLACE FUNCTION cattle_map_deleted_function()
RETURNS TRIGGER AS
$cattle_maps_audit$
	BEGIN
		INSERT INTO cattle_maps_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			)
		VALUES
			( 'd'
			, get_user_id()
			, OLD.id
			, OLD.group_id
			, OLD.target_id
			);
		RETURN NEW;
	END;
$cattle_maps_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_map_deleted_trigger
	ON cattle_maps;
CREATE TRIGGER cattle_map_deleted_trigger
	AFTER DELETE
	ON cattle_maps
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_map_deleted_function();
