CREATE OR REPLACE FUNCTION update_hive(
	IN id INT,
	IN name VARCHAR(255),
	IN queen_color BIT(24))
RETURNS VOID
AS
$body$
	UPDATE hives AS h
		SET name = name
			, queen_color = queen_color
		WHERE h.id = id;
$body$
LANGUAGE SQL;
