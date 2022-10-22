package com.juanjo.rickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResources implements Serializable {
    private Info info;
    private List<Character> results;

    private String error;
}