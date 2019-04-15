CREATE OR REPLACE FUNCTION create_farm(
	IN name VARCHAR(255),
	IN location VARCHAR(255),
	OUT id INT)
AS
$body$
	INSERT INTO farms (name, location)
	VALUES (name, location)
	RETURNING id;
$body$
LANGUAGE SQL;
