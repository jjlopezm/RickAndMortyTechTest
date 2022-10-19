package com.juanjo.rickAndMorty.modelInMemory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterIM {
    private int id;
    private String name;
    
}
