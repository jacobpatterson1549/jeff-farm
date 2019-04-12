CREATE OR REPLACE FUNCTION update_farm(
	IN id INT,
	IN name VARCHAR(255),
	IN location VARCHAR(255))
RETURNS VOID
AS
$body$
	UPDATE farms AS f
		SET name = name,
			location = location
		WHERE f.id = id;
$body$
LANGUAGE SQL;
