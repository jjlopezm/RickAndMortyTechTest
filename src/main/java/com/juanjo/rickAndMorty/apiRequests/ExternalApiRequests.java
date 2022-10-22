package com.juanjo.rickAndMorty.apiRequests;

import com.juanjo.rickAndMorty.config.Config;
import com.juanjo.rickAndMorty.controllers.CharacterEpisodeResponse;
import com.juanjo.rickAndMorty.model.Character;
import com.juanjo.rickAndMorty.model.CharacterResources;
import com.juanjo.rickAndMorty.model.Episode;
import com.juanjo.rickAndMorty.utils.Utils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Component
public class ExternalApiRequests {

    private final ApiRequests.Api apiRequest = ApiRequests.instance();
    private final Pattern patternGetId = Pattern.compile("(.*/)(\\d+)");



    /**
     * Method to get the episodes of a character selected and the first air date where the character appears
     *
     * @param name   The name of the character
     * @param config The config params
     * @return POJO with the result data
     * @throws IOException
     * @throws ParseException
     */
    public CharacterEpisodeResponse getCharactersEpisodes(String name, Config config) throws IOException, ParseException, ExecutionException, InterruptedException {

        List<Character> characterList = getCharactersByName(name, config);

        if (characterList == null)
            return null;

        Set<String> episodeIdsSet = getDistinctEpisodesId(characterList);

        //Get the episodes ordered by air_date
        String episodesQueryEndpoint = config.getApiUrl() + config.getEpisodesEndpoint();
        String queryEpisodesUrl = episodesQueryEndpoint + "/" + String.join(",", episodeIdsSet);
        List<Episode> episodes =
                apiRequest.episodes.getRequest(queryEpisodesUrl).stream().sorted().collect(Collectors.toList());

        String dateFirstOnAir = Utils.transformFormatDate(episodes.get(0).getAirDate(),
                config.getOriginDateFormat(),
                Locale.ENGLISH,
                config.getFinalDateFormat(),
                new Locale("es",""));

        return CharacterEpisodeResponse.builder()
                .name(name)
                .episodes(episodes.stream().map(Episode::getName).collect(Collectors.toList()))
                .first_appearance(dateFirstOnAir)
                .build();
    }

    /**
     * Select the distinct episode ids obtained in character data
     *
     * @param characterList The list of characters with data about episodes
     * @return A set of episodes ids
     */
    private Set<String> getDistinctEpisodesId(List<Character> characterList) {
        return characterList.stream()
                .map(Character::getEpisode)
                .flatMap(List::stream)
                .map(episodeUrl -> {
                    Optional<String> episodeIdOpt = Optional.empty();
                    Matcher m = patternGetId.matcher(episodeUrl);
                    if (m.find()) {
                        episodeIdOpt = Optional.of(m.group(2));
                    }
                    return episodeIdOpt;
                })
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Obtain all characters filtered by a name. It can use iterative or concurrent method to get the data.
     *
     * @param name   The name to filter the character query
     * @param config The app config params
     * @return The list of characters
     * @throws IOException
     */
    private List<Character> getCharactersByName(String name, Config config) throws IOException, ExecutionException, InterruptedException {
        List<Character> characterList = new ArrayList<>();

        String characterResourceUrl = config.getApiUrl() + config.getCharactersEndpoint();
        String standardizedName = name.replace(" ", "%20");
        String queryUrl = characterResourceUrl + "/?name=" + standardizedName;

        ExecutorService executorService = Executors.newFixedThreadPool(config.getNumThreads());

        CharacterResources cr = apiRequest.characterResources.getRequest(queryUrl);

        if (cr.getError() != null) {
            return null;
        }

        characterList = cr.getResults();

        if (config.isConcurrencyEnabled()) {
            characterList.addAll(concurrencyCharacterPagination(queryUrl, executorService, cr.getInfo().getPages()));
        } else {
            characterList.addAll(iterativeCharacterPagination(cr));
        }

        return characterList;
    }

    /**
     * Iterative process to get all characters data when the result of the query is paginated
     *
     * @param cr The first page of character query
     * @return The character data stored in a List
     * @throws IOException
     */
    private List<Character> iterativeCharacterPagination(CharacterResources cr) throws IOException {

        List<Character> characters = new ArrayList<>();
        String queryUrl;

        while (cr.getInfo().getNext() != null) {
            queryUrl = cr.getInfo().getNext();
            cr = apiRequest.characterResources.getRequest(queryUrl);
            characters.addAll(cr.getResults());
            System.out.println(queryUrl);
        }
        return characters;
    }

    /**
     * This method uses the page obtained in characters query to concurrent get the next pages data
     *
     * @param queryUrl        The query to get characters without pagination
     * @param executorService The concurrency manager
     * @param pages           The number of pages to get all data about a character query
     * @return characterList   The character data stored in a List
     */
    private List<Character> concurrencyCharacterPagination(String queryUrl,
                                                                  ExecutorService executorService,
                                                                  int pages) throws InterruptedException, ExecutionException {
        List<Character> charactersAccumulator = new ArrayList<>();
        Collection<Callable<Void>> callables = new ArrayList<>();

        //Start at page 2 because first page is processed yet at this point
        for (int i = 2; i <= pages; i++) {
            String queryLoop = queryUrl + "&page=" + i;
            callables.add(() -> {
                try {
                    CharacterResources crLoop = apiRequest.characterResources.getRequest(queryLoop);
                    charactersAccumulator.addAll(crLoop.getResults());
                    System.out.println(queryLoop);
                } catch (IOException e) {
                    System.out.println("Error getting characters:" + e.getMessage());
                }
                return null;
            });
        }
        executorService.invokeAll(callables);

        return charactersAccumulator;
    }
}
