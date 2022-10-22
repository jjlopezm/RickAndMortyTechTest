package com.juanjo.rickAndMorty.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Episode implements Comparable<Episode>, Serializable {

    private int id;
    private String name;

    @JsonProperty(value = "air_date")
    private String airDate;
    private String episode;
    private List<String> characters;
    private String url;
    private String created;

    @Override
    public int compareTo(Episode episode) {

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("MMMMM dd',' yyyy", Locale.ENGLISH).parse(airDate);
            date2 = new SimpleDateFormat("MMMMM dd',' yyyy", Locale.ENGLISH).parse(episode.airDate);
        } catch (ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
            return 0;
        }
        return date1.compareTo(date2);

    }
}
