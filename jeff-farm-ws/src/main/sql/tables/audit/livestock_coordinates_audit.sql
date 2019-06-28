CREATE TABLE IF NOT EXISTS livestock_coordinates_audit
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


CREATE OR REPLACE FUNCTION livestock_coordinate_inserted_function()
RETURNS TRIGGER AS
$livestock_coordinates_audit$
	BEGIN
		INSERT INTO livestock_coordinates_audit
			( action_type
			, user_id
			, id
			, map_id
			, latitude
			, longitude
			, display_order
			)
		VALUES
			( 'i'
			, get_user_id()
			, NEW.id
			, NEW.map_id
			, NEW.latitude
			, NEW.longitude
			, NEW.display_order
			);
		RETURN NEW;
	END;
$livestock_coordinates_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_coordinate_inserted_trigger
	ON livestock_coordinates;
CREATE TRIGGER livestock_coordinate_inserted_trigger
	AFTER INSERT
	ON livestock_coordinates
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_coordinate_inserted_function();


CREATE OR REPLACE FUNCTION livestock_coordinate_updated_function()
RETURNS TRIGGER AS
$livestock_coordinates_audit$
	BEGIN
		INSERT INTO livestock_coordinates_audit
			( action_type
			, user_id
			, id
			, map_id
			, latitude
			, longitude
			, display_order
			)
		VALUES
			( 'b'
			, get_user_id()
			, OLD.id
			, OLD.map_id
			, OLD.latitude
			, OLD.longitude
			, OLD.display_order
			);
		INSERT INTO livestock_coordinates_audit
			( action_type
			, user_id
			, id
			, map_id
			, latitude
			, longitude
			, display_order
			)
		VALUES
			( 'a'
			, get_user_id()
			, NEW.id
			, NEW.map_id
			, NEW.latitude
			, NEW.longitude
			, NEW.display_order
			);
		RETURN NEW;
	END;
$livestock_coordinates_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_coordinate_updated_trigger
	ON livestock_coordinates;
CREATE TRIGGER livestock_coordinate_updated_trigger
	AFTER UPDATE
	ON livestock_coordinates
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_coordinate_updated_function();


CREATE OR REPLACE FUNCTION livestock_coordinate_deleted_function()
RETURNS TRIGGER AS
$livestock_coordinates_audit$
	BEGIN
		INSERT INTO livestock_coordinates_audit
			( action_type
			, user_id
			, id
			, map_id
			, latitude
			, longitude
			, display_order
			)
		VALUES
			( 'd'
			, get_user_id()
			, OLD.id
			, OLD.map_id
			, OLD.latitude
			, OLD.longitude
			, OLD.display_order
			);
		RETURN NEW;
	END;
$livestock_coordinates_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS livestock_coordinate_deleted_trigger
	ON livestock_coordinates;
CREATE TRIGGER livestock_coordinate_deleted_trigger
	AFTER DELETE
	ON livestock_coordinates
	FOR EACH ROW
	EXECUTE PROCEDURE livestock_coordinate_deleted_function();
