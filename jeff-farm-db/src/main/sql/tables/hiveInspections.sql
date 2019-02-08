-- DROP TABLE hiveInspections;
-- SELECT  * FROM hiveInspections LIMIT 0;

CREATE TABLE IF NOT EXISTS hiveInspections
(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    hiveID INT,
    queenSeen BIT(1),
	eggsSeen BIT(1),
	layingPatternStars INT,
	temperamentStars INT,
	queenCells INT,
	supersedureCells INT,
	swarmCells INT,
	combBuildingStars INT,
	framesSealedBrood INT,
	framesOpenBrood INT,
	framesHoney INT,
	weather VARCHAR(255),
	temperature INT,
	windSpeed INT,
	createdDate DATETIME DEFAULT CURRENT_TIMESTAMP,
	modifiedDate DATETIME ON UPDATE CURRENT_TIMESTAMP,
	active BIT DEFAULT 1
);