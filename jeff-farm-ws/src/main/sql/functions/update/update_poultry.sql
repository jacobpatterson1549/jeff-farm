CREATE OR REPLACE FUNCTION update_poultry
	( IN id INT
	, IN name VARCHAR(255)
	)
RETURNS VOID
AS
$body$
	UPDATE poultry AS h
		SET
			  name = update_poultry.name
		WHERE h.id = update_poultry.id;
$body$
LANGUAGE SQL;
