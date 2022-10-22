package com.juanjo.rickAndMorty.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@Builder
public class CharacterEpisodeResponse {
    private String name;
    private List<String> episodes;
    private String first_appearance;
}
