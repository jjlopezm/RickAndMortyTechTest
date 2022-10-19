package com.juanjo.rickAndMorty.repository;

import com.juanjo.rickAndMorty.controllers.CharacterEpisodeResponse;
import com.juanjo.rickAndMorty.modelInMemory.CharacterIM;
import com.juanjo.rickAndMorty.modelInMemory.EpisodeIM;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryRepo {

    private static List<CharacterIM> characters =
            new ArrayList(Arrays.asList(CharacterIM.builder().name("pepito").id(1).build()));

    private static List<EpisodeIM> episodes =
            new ArrayList(Arrays.asList(
                    EpisodeIM.builder().name("Piloto").air_date(new Date()).characters(Arrays.asList(1, 2, 3)).build(),
                    EpisodeIM.builder().name("Episodio1").air_date(new Date()).characters(Arrays.asList(1)).build(),
                    EpisodeIM.builder().name("Final").air_date(new Date()).characters(Arrays.asList(3)).build()
            )
            );


    public Optional<CharacterEpisodeResponse> findCharacterEpisode(String name) {
        List<CharacterIM> charactersSelected =
                characters.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());

        if (charactersSelected.isEmpty()) {
            return Optional.empty();
        }

        List<EpisodeIM> episodesSelected = episodes.stream()
                .filter(e -> e.getCharacters().contains(charactersSelected.get(0).getId())).sorted().collect(Collectors.toList());

        if (episodesSelected.isEmpty()) {
            return Optional.of(CharacterEpisodeResponse.builder()
                    .name(name)
                    .build());
        }

        return Optional.of(CharacterEpisodeResponse.builder()
                .name(name)
                .episodes(episodesSelected.stream().map(EpisodeIM::getName).collect(Collectors.toList()))
                .first_appearance(episodesSelected.get(0).getAir_date())
                .build());
    }

}
