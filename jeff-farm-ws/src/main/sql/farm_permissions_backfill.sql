-- Give all users access to any farm that no users have access to.
INSERT INTO farm_permissions ( farm_id, user_id )
	SELECT f.id, u.id
	FROM farms AS f
	LEFT JOIN farm_permissions AS fp ON fp.farm_id = f.id
	CROSS JOIN users AS u
	WHERE fp.farm_id IS NULL;
