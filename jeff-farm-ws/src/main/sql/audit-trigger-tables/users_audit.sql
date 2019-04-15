-- DROP TABLE users_audit;
-- SELECT  * FROM users_audit LIMIT 0;
-- 
CREATE TABLE IF NOT EXISTS users_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL -- the user making the change
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT -- the id of the user being changed
	, user_name VARCHAR(20)
	  -- do not audit password
	, first_name VARCHAR(255)
	, last_name VARCHAR(255)
	);


CREATE OR REPLACE FUNCTION user_inserted_function()
RETURNS TRIGGER AS
$users_audit$
	BEGIN
		INSERT INTO users_audit
			( action_type
			, user_id
			, id
			, user_name
			, first_name
			, last_name
			)
		VALUES
			( 'i'
			, CAST(current_setting('jeff_farm_db.user_id') AS INT)
			, NEW.id
			, NEW.user_name
			, NEW.first_name
			, NEW.last_name
			);
		RETURN NEW;
	END;
$users_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS user_inserted_trigger
	ON users;
CREATE TRIGGER user_inserted_trigger
	AFTER INSERT
	ON users
	FOR EACH ROW
	EXECUTE PROCEDURE user_inserted_function();


CREATE OR REPLACE FUNCTION user_updated_function()
RETURNS TRIGGER AS
$users_audit$
	BEGIN
		INSERT INTO users_audit
			( action_type
			, user_id
			, id
			, user_name
			, first_name
			, last_name
			)
		VALUES
			( 'b'
			, CAST(current_setting('jeff_farm_db.user_id') AS INT)
			, OLD.id
			, OLD.user_name
			, OLD.first_name
			, OLD.last_name
			);
		INSERT INTO users_audit
			( action_type
			, user_id
			, id
			, user_name
			, first_name
			, last_name
			)
		VALUES
			( 'a'
			, CAST(current_setting('jeff_farm_db.user_id') AS INT)
			, NEW.id
			, NEW.user_name
			, NEW.first_name
			, NEW.last_name
			);
		RETURN NEW;
	END;
$users_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS user_updated_trigger
	ON users;
CREATE TRIGGER user_updated_trigger
	AFTER UPDATE
	ON users
	FOR EACH ROW
	EXECUTE PROCEDURE user_updated_function();


CREATE OR REPLACE FUNCTION user_deleted_function()
RETURNS TRIGGER AS
$users_audit$
	BEGIN
		INSERT INTO users_audit
			( action_type
			, user_id
			, id
			, user_name
			, first_name
			, last_name
			)
		VALUES
			( 'd'
			, CAST(current_setting('jeff_farm_db.user_id') AS INT)
			, OLD.id
			, OLD.user_name
			, OLD.first_name
			, OLD.last_name
			);
		RETURN NEW;
	END;
$users_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS user_deleted_trigger
	ON users;
CREATE TRIGGER user_deleted_trigger
	AFTER DELETE
	ON users
	FOR EACH ROW
	EXECUTE PROCEDURE user_deleted_function();
