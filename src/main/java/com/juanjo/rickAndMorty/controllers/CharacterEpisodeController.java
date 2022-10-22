package com.juanjo.rickAndMorty.controllers;

import com.juanjo.rickAndMorty.apiRequests.ExternalApiRequests;
import com.juanjo.rickAndMorty.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class CharacterEpisodeController {

    @Autowired
    private Config config;

    @Autowired
    private ExternalApiRequests externalApiRequests;

    @GetMapping(value = "/search-character-appearance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterEpisodeResponse> getCharacters(@RequestParam String name) throws IOException, ParseException, ExecutionException, InterruptedException {

        return Optional.ofNullable(externalApiRequests.getCharactersEpisodes(name, config))
                .map(res -> ResponseEntity.ok().body(res))
                .orElse(ResponseEntity.noContent().build());
    }
}
