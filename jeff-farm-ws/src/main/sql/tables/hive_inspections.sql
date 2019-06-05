-- DROP TABLE hive_inspections;
-- SELECT  * FROM hive_inspections LIMIT 0;

CREATE TABLE IF NOT EXISTS hive_inspections
	( id SERIAL PRIMARY KEY
	, hive_id INT REFERENCES hives (id)
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
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (hive_id)
		REFERENCES hives (id)
	);

CREATE OR REPLACE FUNCTION hive_inspection_update_modified_date_function()
RETURNS TRIGGER AS
$hive_inspections$
	BEGIN
		NEW.modified_date = NOW();
		RETURN NEW;
	END;
$hive_inspections$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS hive_inspection_modified_date_trigger
	ON hive_inspections;
CREATE TRIGGER hive_inspection_modified_date_trigger
	BEFORE UPDATE
	ON hive_inspections
	FOR EACH ROW
	EXECUTE PROCEDURE hive_inspection_update_modified_date_function();
