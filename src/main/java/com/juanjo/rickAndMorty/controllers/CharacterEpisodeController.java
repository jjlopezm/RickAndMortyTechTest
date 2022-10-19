package com.juanjo.rickAndMorty.controllers;

import com.juanjo.rickAndMorty.model.Character;
import com.juanjo.rickAndMorty.model.Episode;
import com.juanjo.rickAndMorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CharacterEpisodeController {

    @Autowired
    private CharacterRepository characterRepo;

    @GetMapping(value = "/search-character-appearance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterEpisodeResponse> getCharacters(@RequestParam String name) {

        return findEpisodesByCharacter(name)
                .map(res -> ResponseEntity.ok().body(res))
                .orElse(ResponseEntity.noContent().build());
    }


    private Optional<CharacterEpisodeResponse> findEpisodesByCharacter(String name) {
        Optional<Character> character = Optional.ofNullable(characterRepo.findByName(name));

        if (character.isPresent()) {
            List<Episode> appears_episodes =
                    character.get().getAppears_episodes().stream().sorted().collect(Collectors.toList());

            CharacterEpisodeResponse cie;
            if (appears_episodes.isEmpty()) {
                cie = CharacterEpisodeResponse.builder()
                        .name(name)
                        .build();
            } else {
                cie = CharacterEpisodeResponse.builder()
                        .name(name)
                        .episodes(appears_episodes.stream().map(Episode::getName).collect(Collectors.toList()))
                        .first_appearance(appears_episodes.iterator().next().getAir_date())
                        .build();
            }
            return Optional.of(cie);
        } else {
            return Optional.empty();
        }
    }
}
