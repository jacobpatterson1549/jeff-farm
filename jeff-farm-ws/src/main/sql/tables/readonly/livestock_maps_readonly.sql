CREATE TABLE IF NOT EXISTS livestock_maps_readonly -- TODO: move these +name tables into the table files?
	( id SERIAL PRIMARY KEY
	, farm_id INT NOT NULL
	, target_id INT NOT NULL
	, target_name VARCHAR(255)
	, created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	, modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	);
