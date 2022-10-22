package com.juanjo.rickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character implements Serializable {
    private int id;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String type;
    private Description origin;
    private Description location;
    private String image;
    private List<String> episode;
}
