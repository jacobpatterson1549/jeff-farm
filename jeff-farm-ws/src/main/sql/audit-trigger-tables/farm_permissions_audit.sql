-- DROP TABLE farm_permissions_audit;
-- SELECT * FROM farm_permissions_audit LIMIT 0;
-- 
CREATE TABLE IF NOT EXISTS farm_permissions_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, fp_farm_id INT NOT NULL
	, fp_user_id INT NOT NULL
	);


CREATE OR REPLACE FUNCTION farm_permission_inserted_function()
RETURNS TRIGGER AS
$farm_permissions_audit$
	BEGIN
		INSERT INTO farm_permissions_audit
			( action_type
			, user_id
			, id
			, fp_farm_id
			, fp_user_id
			)
		VALUES
			( 'i'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, NEW.id
			, NEW.farm_id
			, NEW.user_id
			);
		RETURN NEW;
	END;
$farm_permissions_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS farm_permission_inserted_trigger
	ON farm_permissions;
CREATE TRIGGER farm_permission_inserted_trigger
	AFTER INSERT
	ON farm_permissions
	FOR EACH ROW
	EXECUTE PROCEDURE farm_permission_inserted_function();


CREATE OR REPLACE FUNCTION farm_permission_updated_function()
RETURNS TRIGGER AS
$farm_permissions_audit$
	BEGIN
		INSERT INTO farm_permissions_audit
			( action_type
			, user_id
			, id
			, fp_farm_id
			, fp_user_id
			)
		VALUES
			( 'b'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, OLD.id
			, OLD.farm_id
			, OLD.user_id
			);
		INSERT INTO farm_permissions_audit
			( action_type
			, user_id
			, id
			, fp_farm_id
			, fp_user_id
			)
		VALUES
			( 'a'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, NEW.id
			, NEW.farm_id
			, NEW.user_id
			);
		RETURN NEW;
	END;
$farm_permissions_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS farm_permission_updated_trigger
	ON farm_permissions;
CREATE TRIGGER farm_permission_updated_trigger
	AFTER UPDATE
	ON farm_permissions
	FOR EACH ROW
	EXECUTE PROCEDURE farm_permission_updated_function();


CREATE OR REPLACE FUNCTION farm_permission_deleted_function()
RETURNS TRIGGER AS
$farm_permissions_audit$
	BEGIN
		INSERT INTO farm_permissions_audit
			( action_type
			, user_id
			, id
			, fp_farm_id
			, fp_user_id
			)
		VALUES
			( 'd'
			, CAST(current_setting('jeff_farm_ws.user_id') AS INT)
			, OLD.id
			, OLD.farm_id
			, OLD.user_id
			);
		RETURN NEW;
	END;
$farm_permissions_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS farm_permission_deleted_trigger
	ON farm_permissions;
CREATE TRIGGER farm_permission_deleted_trigger
	AFTER DELETE
	ON farm_permissions
	FOR EACH ROW
	EXECUTE PROCEDURE farm_permission_deleted_function();
