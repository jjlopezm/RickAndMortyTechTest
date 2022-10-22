# Rick & Morty Tech Exercise

The idea of this project is get information about the episodes of the Rick & Morty TV show where appears some 
characters. To do that we will use this resource https://rickandmortyapi.com/documentation/


## Requirements
 - Java 11 or later
 - Maven

## Get executable JAR
```shell
$> mvn clean install
```

This command will generate the file "RickAndMortyAPI.jar" that can be executed in the "target" directory

#Additional configuration
It is possible to configure some parameters. The default ones are in directory
```shell
./src/main/resources/application.properties
```

The file structure is the following:
```properties
#api info
api.url=https://rickandmortyapi.com/api
api.characters_endpoint=/character
api.episodes_endpoint=/episode

#concurrency
api.concurrency_enabled=false
api.num_threads=10

#date formats
api.origin_date_format=MMMMM dd',' yyyy
api.final_date_format=dd MMMMM yyyy
```

As you can see, it is possible to configure if you want to use concurrency and the number of threads to use. It will 
be used to get Characters data from the resource api.

It is possible to use your own properties file to run this app or use the default one.

Example:
```properties
api.concurrency_enabled=true
api.num_threads=10
```

The rest of parameters exists only to get updated if the resources API change.

## Run
You can run the app with the default config...
```shell
$> java -jar target/RickAndMortyAPI.jar
```

Or you can use your own config...
```shell
$> java -jar target/RickAndMortyAPI.jar --spring.config.location=/tmp/myconfig.properties
```

## How to test
Just type in a browser... 
```
http://localhost:8080/search-character-appearance?name=Rick Sanchez
``` 
...and the response will be something like

```json
{
  "name": "Rick Sanchez",
  "episodes": [
    "Pilot",
    "Lawnmower Dog",
    ...
  ],
  "first_appearance": "2 diciembre 2013"
}
```


