-- DROP TABLE poultry_inspections_audit;
-- SELECT * FROM poultry_inspections_audit LIMIT 0;
-- 
CREATE TABLE IF NOT EXISTS poultry_inspections_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, group_id INT NOT NULL
	, target_id INT NOT NULL
	, bird_count INT NOT NULL
	, egg_count INT NOT NULL
	);


CREATE OR REPLACE FUNCTION poultry_inspection_inserted_function()
RETURNS TRIGGER AS
$poultry_inspections_audit$
	BEGIN
		INSERT INTO poultry_inspections_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			, bird_count
			, egg_count
			)
		VALUES
			( 'i'
			, get_user_id()
			, NEW.id
			, NEW.group_id
			, NEW.target_id
			, NEW.bird_count
			, NEW.egg_count
			);
		RETURN NEW;
	END;
$poultry_inspections_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inspection_inserted_trigger
	ON poultry_inspections;
CREATE TRIGGER poultry_inspection_inserted_trigger
	AFTER INSERT
	ON poultry_inspections
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inspection_inserted_function();


CREATE OR REPLACE FUNCTION poultry_inspection_updated_function()
RETURNS TRIGGER AS
$poultry_inspections_audit$
	BEGIN
		INSERT INTO poultry_inspections_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			, bird_count
			, egg_count
			)
		VALUES
			( 'b'
			, get_user_id()
			, OLD.id
			, OLD.group_id
			, OLD.target_id
			, OLD.bird_count
			, OLD.egg_count
			);
		INSERT INTO poultry_inspections_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			, bird_count
			, egg_count
			)
		VALUES
			( 'a'
			, get_user_id()
			, NEW.id
			, NEW.group_id
			, NEW.target_id
			, NEW.bird_count
			, NEW.egg_count
			);
		RETURN NEW;
	END;
$poultry_inspections_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inspection_updated_trigger
	ON poultry_inspections;
CREATE TRIGGER poultry_inspection_updated_trigger
	AFTER UPDATE
	ON poultry_inspections
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inspection_updated_function();


CREATE OR REPLACE FUNCTION poultry_inspection_deleted_function()
RETURNS TRIGGER AS
$poultry_inspections_audit$
	BEGIN
		INSERT INTO poultry_inspections_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			, bird_count
			, egg_count
			)
		VALUES
			( 'd'
			, get_user_id()
			, OLD.id
			, OLD.group_id
			, OLD.target_id
			, OLD.bird_count
			, OLD.egg_count
			);
		RETURN NEW;
	END;
$poultry_inspections_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS poultry_inspection_deleted_trigger
	ON poultry_inspections;
CREATE TRIGGER poultry_inspection_deleted_trigger
	AFTER DELETE
	ON poultry_inspections
	FOR EACH ROW
	EXECUTE PROCEDURE poultry_inspection_deleted_function();
