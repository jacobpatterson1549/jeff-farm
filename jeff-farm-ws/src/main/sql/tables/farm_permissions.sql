-- DROP TABLE farm_permissions;
-- SELECT  * FROM farm_permissions LIMIT 0;

CREATE TABLE IF NOT EXISTS farm_permissions
	( farm_id INT NOT NULL
	, user_id INT NOT NULL
	, PRIMARY KEY (farm_id, user_id)

	, FOREIGN KEY (farm_id)
		REFERENCES farms (id)
	, FOREIGN KEY (user_id)
		REFERENCES users (id)
	);
