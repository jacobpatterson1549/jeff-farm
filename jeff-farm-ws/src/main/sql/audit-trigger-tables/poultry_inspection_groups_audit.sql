-- DROP TABLE poultry_inspection_groups_audit;
-- SELECT * FROM poultry_inspection_groups_audit LIMIT 0;
-- 
CREATE TABLE IF NOT EXISTS poultry_inspection_groups_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, farm_id INT
	, notes VARCHAR(4095)
	);


CREATE OR REPLACE FUNCTION poultry_inspection_group_inserted_function()
RETURNS TRIGGER AS
$poultry_inspection_groups_audit$
	BEGIN
		INSERT INTO poultry_inspection_groups_audit
			( action_type
			, user_id
			, id
			, farm_id
			, notes
			)
		VALUES
			( 'i'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, NEW.id
			, NEW.farm_id
			, NEW.notes
			);
		RETURN NEW;
	END;
$poultry_inspection_groups_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inspection_group_inserted_trigger
	ON poultry_inspection_groups;
CREATE TRIGGER poultry_inspection_group_inserted_trigger
	AFTER INSERT
	ON poultry_inspection_groups
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inspection_group_inserted_function();


CREATE OR REPLACE FUNCTION poultry_inspection_group_updated_function()
RETURNS TRIGGER AS
$poultry_inspection_groups_audit$
	BEGIN
		INSERT INTO poultry_inspection_groups_audit
			( action_type
			, user_id
			, id
			, notes
			)
		VALUES
			( 'b'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, OLD.id
			, OLD.notes
			);
		INSERT INTO poultry_inspection_group_audit
			( action_type
			, user_id
			, id
			, notes
			)
		VALUES
			( 'a'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, NEW.id
			, NEW.notes
			);
		RETURN NEW;
	END;
$poultry_inspection_groups_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inspection_group_updated_trigger
	ON poultry_inspection_groups;
CREATE TRIGGER poultry_inspection_group_updated_trigger
	AFTER UPDATE
	ON poultry_inspection_groups
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inspection_group_updated_function();


CREATE OR REPLACE FUNCTION poultry_inspection_group_deleted_function()
RETURNS TRIGGER AS
$poultry_inspection_groups_audit$
	BEGIN
		INSERT INTO poultry_inspection_groups_audit
			( action_type
			, user_id
			, id
			, notes
			)
		VALUES
			( 'd'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, OLD.id
			, OLD.notes
			);
		RETURN NEW;
	END;
$poultry_inspection_groups_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inspection_group_deleted_trigger
	ON poultry_inspection_groups;
CREATE TRIGGER poultry_inspection_group_deleted_trigger
	AFTER DELETE
	ON poultry_inspection_groups
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inspection_group_deleted_function();
