package com.juanjo.rickAndMorty.modelInMemory;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class EpisodeIM implements Comparable<EpisodeIM>{
    private int id;
    private String name;
    private Date air_date;
    private String episode;
    private List<Integer> characters;

    @Override
    public int compareTo(EpisodeIM episode) {
        return air_date.compareTo(episode.air_date);
    }
}
