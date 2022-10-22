package com.juanjo.rickAndMorty.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "api")
@Component
@Data
public class Config {

    @Value("${api.num_threads:10}")
    private int numThreads;

    @Value("${api.concurrency_enabled:true}")
    private boolean concurrencyEnabled;

    @Value("${api.url:https://rickandmortyapi.com/api}")
    private String apiUrl;

    @Value("${api.characters_endpoint:/character}")
    private String charactersEndpoint;

    @Value("${api.episodes_endpoint:/episode}")
    private String episodesEndpoint;

    @Value("${api.origin_date_format:MMMMM dd',' yyyy}")
    private String originDateFormat;

    @Value("${api.final_date_format:d MMMMM yyyy}")
    private String finalDateFormat;
}
