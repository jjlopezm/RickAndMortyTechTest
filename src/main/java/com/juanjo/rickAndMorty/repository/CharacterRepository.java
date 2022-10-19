package com.juanjo.rickAndMorty.repository;

import com.juanjo.rickAndMorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Character findByName(String name);
}
