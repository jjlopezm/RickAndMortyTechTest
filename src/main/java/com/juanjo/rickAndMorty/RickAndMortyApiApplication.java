package com.juanjo.rickAndMorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.juanjo.rickAndMorty.config.Config")
public class RickAndMortyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RickAndMortyApiApplication.class, args);
    }

}
