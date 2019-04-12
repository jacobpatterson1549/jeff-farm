CREATE OR REPLACE FUNCTION create_farm(
	IN name VARCHAR(255),
	IN location VARCHAR(255))
RETURNS INT
AS
$body$
	INSERT INTO farms (name, location)
	VALUES (name, location)
	RETURNING id;
$body$
LANGUAGE SQL;
