package com.juanjo.rickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Episodes extends ArrayList<Episode> {
}


