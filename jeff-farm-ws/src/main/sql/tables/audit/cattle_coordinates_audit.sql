CREATE TABLE IF NOT EXISTS cattle_coordinates_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, map_id INT
	, latitude DOUBLE PRECISION
	, longitude DOUBLE PRECISION
	, display_order INT
	);


CREATE OR REPLACE FUNCTION cattle_coordinate_inserted_function()
RETURNS TRIGGER AS
$cattle_coordinates_audit$
	BEGIN
		INSERT INTO cattle_coordinates_audit
			( action_type
			, user_id
			, id
			, map_id
			, target_id
			, latitude
			, longitude
			, display_order
			)
		VALUES
			( 'i'
			, get_user_id()
			, NEW.id
			, NEW.map_id
			, NEW.target_id
			, NEW.latitude
			, NEW.longitude
			, NEW.display_order
			);
		RETURN NEW;
	END;
$cattle_coordinates_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_coordinate_inserted_trigger
	ON cattle_coordinates;
CREATE TRIGGER cattle_coordinate_inserted_trigger
	AFTER INSERT
	ON cattle_coordinates
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_coordinate_inserted_function();


CREATE OR REPLACE FUNCTION cattle_coordinate_updated_function()
RETURNS TRIGGER AS
$cattle_coordinates_audit$
	BEGIN
		INSERT INTO cattle_coordinates_audit
			( action_type
			, user_id
			, id
			, map_id
			, target_id
			, latitude
			, longitude
			, display_order
			)
		VALUES
			( 'b'
			, get_user_id()
			, OLD.id
			, OLD.map_id
			, OLD.target_id
			, OLD.latitude
			, OLD.longitude
			, OLD.display_order
			);
		INSERT INTO cattle_coordinates_audit
			( action_type
			, user_id
			, id
			, map_id
			, target_id
			, latitude
			, longitude
			, display_order
			)
		VALUES
			( 'a'
			, get_user_id()
			, NEW.id
			, NEW.map_id
			, NEW.target_id
			, NEW.latitude
			, NEW.longitude
			, NEW.display_order
			);
		RETURN NEW;
	END;
$cattle_coordinates_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_coordinate_updated_trigger
	ON cattle_coordinates;
CREATE TRIGGER cattle_coordinate_updated_trigger
	AFTER UPDATE
	ON cattle_coordinates
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_coordinate_updated_function();


CREATE OR REPLACE FUNCTION cattle_coordinate_deleted_function()
RETURNS TRIGGER AS
$cattle_coordinates_audit$
	BEGIN
		INSERT INTO cattle_coordinates_audit
			( action_type
			, user_id
			, id
			, map_id
			, target_id
			, latitude
			, longitude
			, display_order
			)
		VALUES
			( 'd'
			, get_user_id()
			, OLD.id
			, OLD.map_id
			, OLD.target_id
			, OLD.latitude
			, OLD.longitude
			, OLD.display_order
			);
		RETURN NEW;
	END;
$cattle_coordinates_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cattle_coordinate_deleted_trigger
	ON cattle_coordinates;
CREATE TRIGGER cattle_coordinate_deleted_trigger
	AFTER DELETE
	ON cattle_coordinates
	FOR EACH ROW
	EXECUTE PROCEDURE cattle_coordinate_deleted_function();
