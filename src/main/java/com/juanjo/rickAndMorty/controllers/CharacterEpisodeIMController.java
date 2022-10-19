package com.juanjo.rickAndMorty.controllers;


import com.juanjo.rickAndMorty.repository.InMemoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterEpisodeIMController {

    @Autowired
    private InMemoryRepo repo;

	@GetMapping("/in_memory/search-character-appearance")
    public ResponseEntity<CharacterEpisodeResponse> getCharacters(@RequestParam(required = false) String name) {

        return repo.findCharacterEpisode(name)
                .map( res -> ResponseEntity.ok().body(res))
                .orElse(ResponseEntity.noContent().build());
    }
}
