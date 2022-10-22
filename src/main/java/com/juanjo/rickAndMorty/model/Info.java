package com.juanjo.rickAndMorty.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Info implements Serializable {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
