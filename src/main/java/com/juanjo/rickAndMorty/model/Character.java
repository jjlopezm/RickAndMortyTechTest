package com.juanjo.rickAndMorty.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "episode_characters",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id"))
    private Set<Episode> appears_episodes;

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

    public Set<Episode> getAppears_episodes() {
        return appears_episodes;
    }

    public void setAppears_episodes(Set<Episode> appears_episodes) {
        this.appears_episodes = appears_episodes;
    }
}
