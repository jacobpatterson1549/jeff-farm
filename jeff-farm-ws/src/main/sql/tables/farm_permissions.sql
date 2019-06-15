-- DROP TABLE farm_permissions;
-- SELECT  * FROM farm_permissions LIMIT 0;

CREATE TABLE IF NOT EXISTS farm_permissions
	( farm_id INT NOT NULL
	, user_id INT NOT NULL
	, PRIMARY KEY (farm_id, user_id)
	);
