-- DROP TABLE poultry_inspection_readonly;
-- SELECT  * FROM poultry_inspection_readonly LIMIT 0;

CREATE TABLE IF NOT EXISTS poultry_inspection_readonly
	( id INT
	, group_id INT
	, target_id INT
	, target_name VARCHAR(255)
	, bird_count INT
	, egg_count INT
	, created_date TIMESTAMP
	, modified_date TIMESTAMP
	);
