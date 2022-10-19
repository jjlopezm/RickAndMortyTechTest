package com.juanjo.rickAndMorty;

import com.juanjo.rickAndMorty.model.Episode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RickAndMortyApiApplicationTests {

	Instant now = Instant.now();
	Instant yesterday = now.minus(1, ChronoUnit.DAYS);
	Instant tomorrow = now.plus(1, ChronoUnit.DAYS);

	@Test
	void contextLoads() {
	}

	@Test
	void testDateOrder(){
		Episode e1 = new Episode(1, "e1", Date.from(now), "E01", null);
		Episode e2 = new Episode(2, "e2", Date.from(yesterday), "E02", null);
		Episode e3 = new Episode(3, "e3", Date.from(tomorrow), "E03", null);

		List<Episode> episodes = new ArrayList<>(Arrays.asList(e1,e2,e3));
		Collections.shuffle(episodes);

		Assertions.assertEquals(2, episodes.stream().sorted().collect(Collectors.toList()).get(0).getId());
	}

}
