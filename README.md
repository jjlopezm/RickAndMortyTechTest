# Rick & Morty Tech Exercise

This is a simple example of how to implement an API REST Get operation using an embedded H2 database with some data.

The idea is to get information about the episodes of the Rick & Morty TV show where appears some characters.

## Database Structure
1. Table characters that contains the names of the characters.
2. Table episodes with data about name, air date and other additional info about the episodes
3. Relational "many to many" table named episode_characters that contains id of the character and id of the episode.

The file that contains the initial data can be found in:

```shell
./src/main/resources/data.sql
```

## Requirements
 - Java 11 or later
 - Maven

## Get executable JAR
```shell
$> mvn clean install
```

This command will generate the file "RickAndMortyAPI.jar" that can be executed in the "target" directory

## Run
```shell
$> java -jar target/RickAndMortyAPI.jar
```

## How to test
Just type in a browser... [http://localhost:8080/search-character-appearance?name=Rick Sanchez] and it will return 
something like

```json
{
    "name": "Rick Sanchez",
    "episodes": [
        "Pilot",
        "Episode2"
    ],
    "first_appearance": "2015-05-18"
}
```

It is possible to test with the names:
- Rick Sanchez
- Morty
- Snuffles
- Juanjo


