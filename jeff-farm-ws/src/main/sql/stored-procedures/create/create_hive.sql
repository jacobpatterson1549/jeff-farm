CREATE OR REPLACE FUNCTION create_hive(
	IN farm_id INT,
	IN name VARCHAR(255),
	IN queen_color BIT(24),
	OUT id INT)
AS
$body$
	INSERT INTO hives (farm_id, name, queen_color)
		SELECT farm_id, name, queen_color
		FROM farms AS f
		WHERE f.id = farm_id
		RETURNING id;
$body$
LANGUAGE SQL;
