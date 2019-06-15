DROP FUNCTION IF EXISTS read_poultry_list;
CREATE FUNCTION read_poultry_list
	( IN farm_id INT
	, IN user_id INT
	)
RETURNS SETOF poultry
AS
$body$
	SELECT
		  h.id
		, h.farm_id
		, h.name
		, h.created_date
		, h.modified_date
	FROM poultry AS h
	WHERE permission_check_farm(set_user_id(user_id), farm_id)
		AND h.farm_id = read_poultry_list.farm_id;
$body$
LANGUAGE SQL;
