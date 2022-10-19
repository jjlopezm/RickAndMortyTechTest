package com.juanjo.rickAndMorty.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CharacterEpisodeResponse {
    private String name;
    private List<String> episodes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMMM 'de' yyyy")
    private Date first_appearance;
}
