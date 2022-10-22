package com.juanjo.rickAndMorty.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Description implements Serializable {
    private String name;
    private String url;
}
