CREATE OR REPLACE FUNCTION update_farm
	( IN id INT
	, IN name VARCHAR(255)
	, IN location VARCHAR(255)
	)
RETURNS VOID
AS
$body$
	UPDATE farms AS f
		SET
			  name = update_farm.name
			, location = update_farm.location
		WHERE f.id = update_farm.id;
$body$
LANGUAGE SQL;
