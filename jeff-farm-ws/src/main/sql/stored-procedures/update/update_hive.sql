CREATE OR REPLACE FUNCTION update_hive(
	IN id INT,
	IN name VARCHAR(255),
	IN queen_color BIT(24))
RETURNS VOID
AS
$body$
	UPDATE hives AS h
		SET name = update_hive.name
			, queen_color = update_hive.queen_color
		WHERE h.id = update_hive.id;
$body$
LANGUAGE SQL;
