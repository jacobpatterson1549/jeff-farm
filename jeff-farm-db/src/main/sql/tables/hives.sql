-- DROP TABLE hives;
-- SELECT  * FROM hives LIMIT 0;

CREATE TABLE IF NOT EXISTS hives
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	farm_id INT REFERENCES farms (id),
	name VARCHAR(255) NOT NULL,
	queen_color BIT(24) NOT NULL, -- 0-16777215 (0-0xFFFFFFFF) = 4*6=24 bits Use SELECT HEX(queen_color) to view base16 color.
	created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	modified_date DATETIME ON UPDATE CURRENT_TIMESTAMP,
	active BIT DEFAULT 1
);