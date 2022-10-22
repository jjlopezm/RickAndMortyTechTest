package com.juanjo.rickAndMorty.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class EpisodeTests {

    @Test
    void testDateOrder(){
        Episode e1 = new Episode(1,"Episode1", "April 3, 2013", "",null,null, null );
        Episode e2 = new Episode(2,"Episode2", "April 1, 2013", "",null,null, null );
        Episode e3 = new Episode(3,"Episode2", "April 2, 2013", "",null,null, null );

        List<Episode> episodes = new ArrayList<>(Arrays.asList(e1,e2,e3));
        Collections.shuffle(episodes);

        Assertions.assertEquals(2, episodes.stream().sorted().collect(Collectors.toList()).get(0).getId());
    }
}
