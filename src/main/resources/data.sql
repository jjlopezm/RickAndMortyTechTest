DROP TABLE IF EXISTS episode_characters;
DROP TABLE IF EXISTS episodes;
DROP TABLE IF EXISTS characters;

CREATE TABLE characters (id INT PRIMARY KEY, name VARCHAR(255));
CREATE TABLE episodes (id INT PRIMARY KEY, name VARCHAR(255), air_date VARCHAR(255), episode VARCHAR(255));
CREATE TABLE episode_characters (character_id INT, episode_id INT);

INSERT INTO characters (id, name) VALUES (1, 'Rick Sanchez');
INSERT INTO characters (id, name) VALUES (2, 'Morty');
INSERT INTO characters (id, name) VALUES (3, 'Juanjo');
INSERT INTO characters (id, name) VALUES (4, 'Snuffles');

INSERT INTO episodes (id, name, air_date, episode) VALUES (1, 'Pilot', '2013-12-02', 'T1E01');
INSERT INTO episodes (id, name, air_date, episode) VALUES (2, 'Episode2', '2014-01-05', 'T1E02');
INSERT INTO episodes (id, name, air_date, episode) VALUES (3, 'Final T1', '2014-04-14', 'T1E20');

INSERT INTO episode_characters (character_id, episode_id) VALUES (1, 1);
INSERT INTO episode_characters (character_id, episode_id) VALUES (2, 1);
INSERT INTO episode_characters (character_id, episode_id) VALUES (4, 2);
INSERT INTO episode_characters (character_id, episode_id) VALUES (4, 3);
INSERT INTO episode_characters (character_id, episode_id) VALUES (1, 2);
INSERT INTO episode_characters (character_id, episode_id) VALUES (2, 2);

