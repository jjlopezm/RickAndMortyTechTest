package com.juanjo.rickAndMorty.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "episodes")
public class Episode implements Comparable<Episode> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Date air_date;
    private String episode;

    public Episode() {
    }

    public Episode(int id, String name, Date air_date, String episode, Set<Character> characters) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
        this.characters = characters;
    }

    @ManyToMany(mappedBy = "appears_episodes")
    private Set<Character> characters;

    @Override
    public int compareTo(Episode episode) {
        return air_date.compareTo(episode.air_date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAir_date() {
        return air_date;
    }

    public void setAir_date(Date air_date) {
        this.air_date = air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }
}
