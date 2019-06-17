-- DROP TABLE farm_permissions_readonly;
-- SELECT  * FROM farm_permissions_readonly LIMIT 0;

CREATE TABLE IF NOT EXISTS farm_permissions_readonly
	( id INT
	, farm_id INT
	, user_name VARCHAR(20)
	, created_date TIMESTAMP
	, modified_date TIMESTAMP
	);
