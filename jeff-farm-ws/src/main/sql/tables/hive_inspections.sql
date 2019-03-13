-- DROP TABLE hive_inspections;
-- SELECT  * FROM hive_inspections LIMIT 0;

CREATE TABLE IF NOT EXISTS hive_inspections
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	hive_id INT REFERENCES hives (id),
	queen_seen BIT(1),
	eggs_seen BIT(1),
	laying_pattern_stars INT,
	temperament_stars INT,
	queen_cells INT,
	supersedure_cells INT,
	swarm_cells INT,
	comb_building_stars INT,
	frames_sealed_brood INT,
	frames_open_brood INT,
	frames_honey INT,
	weather VARCHAR(255),
	temperature_f INT,
	wind_speed_mph INT,
	created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	modified_date DATETIME ON UPDATE CURRENT_TIMESTAMP,

	FOREIGN KEY (hive_id)
		REFERENCES hives (id)
);