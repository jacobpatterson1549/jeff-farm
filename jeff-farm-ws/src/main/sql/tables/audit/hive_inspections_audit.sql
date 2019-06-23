-- DROP TABLE hive_inspections_audit;
-- SELECT * FROM hive_inspections_audit LIMIT 0;
-- 
CREATE TABLE IF NOT EXISTS hive_inspections_audit
	( audit_id SERIAL PRIMARY KEY
	, action_type CHAR(1) NOT NULL -- i (insert), b (before update), a (after update), d (delete)
	, user_id INT NOT NULL
	, action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, id INT
	, group_id INT
	, target_id INT
	, queen_seen BOOLEAN
	, eggs_seen BOOLEAN
	, laying_pattern_stars INT
	, temperament_stars INT
	, queen_cells INT
	, supersedure_cells INT
	, swarm_cells INT
	, comb_building_stars INT
	, frames_sealed_brood INT
	, frames_open_brood INT
	, frames_honey INT
	, weather VARCHAR(255)
	, temperature_f INT
	, wind_speed_mph INT
	);


CREATE OR REPLACE FUNCTION hive_inspection_inserted_function()
RETURNS TRIGGER AS
$hive_inspections_audit$
	BEGIN
		INSERT INTO hive_inspections_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			, queen_seen
            , eggs_seen
            , laying_pattern_stars
            , temperament_stars
            , queen_cells
            , supersedure_cells
            , swarm_cells
            , comb_building_stars
            , frames_sealed_brood
            , frames_open_brood
            , frames_honey
            , weather
            , temperature_f
            , wind_speed_mph
			)
		VALUES
			( 'i'
			, get_user_id()
			, NEW.id
			, NEW.group_id
			, NEW.target_id
			, NEW.queen_seen
            , NEW.eggs_seen
            , NEW.laying_pattern_stars
            , NEW.temperament_stars
            , NEW.queen_cells
            , NEW.supersedure_cells
            , NEW.swarm_cells
            , NEW.comb_building_stars
            , NEW.frames_sealed_brood
            , NEW.frames_open_brood
            , NEW.frames_honey
            , NEW.weather
            , NEW.temperature_f
            , NEW.wind_speed_mph
			);
		RETURN NEW;
	END;
$hive_inspections_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inspection_inserted_trigger
	ON hive_inspections;
CREATE TRIGGER hive_inspection_inserted_trigger
	AFTER INSERT
	ON hive_inspections
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inspection_inserted_function();


CREATE OR REPLACE FUNCTION hive_inspection_updated_function()
RETURNS TRIGGER AS
$hive_inspections_audit$
	BEGIN
		INSERT INTO hive_inspections_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			, queen_seen
            , eggs_seen
            , laying_pattern_stars
            , temperament_stars
            , queen_cells
            , supersedure_cells
            , swarm_cells
            , comb_building_stars
            , frames_sealed_brood
            , frames_open_brood
            , frames_honey
            , weather
            , temperature_f
            , wind_speed_mph
			)
		VALUES
			( 'b'
			, get_user_id()
			, OLD.id
			, OLD.group_id
			, OLD.target_id
			, OLD.queen_seen
            , OLD.eggs_seen
            , OLD.laying_pattern_stars
            , OLD.temperament_stars
            , OLD.queen_cells
            , OLD.supersedure_cells
            , OLD.swarm_cells
            , OLD.comb_building_stars
            , OLD.frames_sealed_brood
            , OLD.frames_open_brood
            , OLD.frames_honey
            , OLD.weather
            , OLD.temperature_f
            , OLD.wind_speed_mph
			);
		INSERT INTO hive_inspections_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			, queen_seen
            , eggs_seen
            , laying_pattern_stars
            , temperament_stars
            , queen_cells
            , supersedure_cells
            , swarm_cells
            , comb_building_stars
            , frames_sealed_brood
            , frames_open_brood
            , frames_honey
            , weather
            , temperature_f
            , wind_speed_mph
			)
		VALUES
			( 'a'
			, get_user_id()
			, NEW.id
			, NEW.group_id
			, NEW.target_id
			, NEW.queen_seen
            , NEW.eggs_seen
            , NEW.laying_pattern_stars
            , NEW.temperament_stars
            , NEW.queen_cells
            , NEW.supersedure_cells
            , NEW.swarm_cells
            , NEW.comb_building_stars
            , NEW.frames_sealed_brood
            , NEW.frames_open_brood
            , NEW.frames_honey
            , NEW.weather
            , NEW.temperature_f
            , NEW.wind_speed_mph
			);
		RETURN NEW;
	END;
$hive_inspections_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inspection_updated_trigger
	ON hive_inspections;
CREATE TRIGGER hive_inspection_updated_trigger
	AFTER UPDATE
	ON hive_inspections
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inspection_updated_function();


CREATE OR REPLACE FUNCTION hive_inspection_deleted_function()
RETURNS TRIGGER AS
$hive_inspections_audit$
	BEGIN
		INSERT INTO hive_inspections_audit
			( action_type
			, user_id
			, id
			, group_id
			, target_id
			, queen_seen
            , eggs_seen
            , laying_pattern_stars
            , temperament_stars
            , queen_cells
            , supersedure_cells
            , swarm_cells
            , comb_building_stars
            , frames_sealed_brood
            , frames_open_brood
            , frames_honey
            , weather
            , temperature_f
            , wind_speed_mph
			)
		VALUES
			( 'd'
			, get_user_id()
			, OLD.id
			, OLD.group_id
			, OLD.target_id
			, OLDqueen_seen
            , OLDeggs_seen
            , OLDlaying_pattern_stars
            , OLDtemperament_stars
            , OLDqueen_cells
            , OLDsupersedure_cells
            , OLDswarm_cells
            , OLDcomb_building_stars
            , OLDframes_sealed_brood
            , OLDframes_open_brood
            , OLDframes_honey
            , OLDweather
            , OLDtemperature_f
            , OLDwind_speed_mph
			);
		RETURN NEW;
	END;
$hive_inspections_audit$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inspection_deleted_trigger
	ON hive_inspections;
CREATE TRIGGER hive_inspection_deleted_trigger
	AFTER DELETE
	ON hive_inspections
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inspection_deleted_function();
