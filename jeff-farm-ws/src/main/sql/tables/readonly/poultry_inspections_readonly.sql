-- DROP TABLE poultry_inspections_readonly;
-- SELECT  * FROM poultry_inspections_readonly LIMIT 0;

CREATE TABLE IF NOT EXISTS poultry_inspections_readonly
	( id INT
	, group_id INT
	, target_id INT
	, target_name VARCHAR(255)
	, bird_count INT
	, egg_count INT
	, created_date TIMESTAMP
	, modified_date TIMESTAMP
	);
