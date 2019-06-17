-- DROP TABLE farm_permissions_readonly;
-- SELECT  * FROM farm_permissions_readonly LIMIT 0;

CREATE TABLE IF NOT EXISTS farm_permissions_readonly
	( id INT
	, group_id INT
	, target_id INT
	, bird_count INT
	, egg_count INT
	, created_date TIMESTAMP
	, modified_date TIMESTAMP
	);
