CREATE TABLE IF NOT EXISTS hive_inspection_groups_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, farm_id INT
	, notes VARCHAR(4095)
	);


CREATE OR REPLACE FUNCTION hive_inspection_group_inserted_function()
RETURNS TRIGGER AS
$hive_inspection_groups_audit$
	BEGIN
		INSERT INTO hive_inspection_groups_audit
			( action_type
			, user_id
			, id
			, farm_id
			, notes
			)
		VALUES
			( 'i'
			, get_user_id()
			, NEW.id
			, NEW.farm_id
			, NEW.notes
			);
		RETURN NEW;
	END;
$hive_inspection_groups_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inspection_group_inserted_trigger
	ON hive_inspection_groups;
CREATE TRIGGER hive_inspection_group_inserted_trigger
	AFTER INSERT
	ON hive_inspection_groups
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inspection_group_inserted_function();


CREATE OR REPLACE FUNCTION hive_inspection_group_updated_function()
RETURNS TRIGGER AS
$hive_inspection_groups_audit$
	BEGIN
		INSERT INTO hive_inspection_groups_audit
			( action_type
			, user_id
			, id
			, notes
			)
		VALUES
			( 'b'
			, get_user_id()
			, OLD.id
			, OLD.notes
			);
		INSERT INTO hive_inspection_groups_audit
			( action_type
			, user_id
			, id
			, notes
			)
		VALUES
			( 'a'
			, get_user_id()
			, NEW.id
			, NEW.notes
			);
		RETURN NEW;
	END;
$hive_inspection_groups_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inspection_group_updated_trigger
	ON hive_inspection_groups;
CREATE TRIGGER hive_inspection_group_updated_trigger
	AFTER UPDATE
	ON hive_inspection_groups
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inspection_group_updated_function();


CREATE OR REPLACE FUNCTION hive_inspection_group_deleted_function()
RETURNS TRIGGER AS
$hive_inspection_groups_audit$
	BEGIN
		INSERT INTO hive_inspection_groups_audit
			( action_type
			, user_id
			, id
			, notes
			)
		VALUES
			( 'd'
			, get_user_id()
			, OLD.id
			, OLD.notes
			);
		RETURN NEW;
	END;
$hive_inspection_groups_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inspection_group_deleted_trigger
	ON hive_inspection_groups;
CREATE TRIGGER hive_inspection_group_deleted_trigger
	AFTER DELETE
	ON hive_inspection_groups
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inspection_group_deleted_function();
