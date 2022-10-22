package com.juanjo.rickAndMorty.apiRequests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juanjo.rickAndMorty.model.CharacterResources;
import com.juanjo.rickAndMorty.model.Episodes;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class ApiRequests<T> {

    private final static ObjectMapper mapper = new ObjectMapper();
    private final Class<T> valueType;

    private ApiRequests(Class<T> valueType) {

        this.valueType = valueType;
    }

    public static class Api {
        private Api() {
            this.characterResources = new ApiRequests<>(CharacterResources.class);
            this.episodes = new ApiRequests<>(Episodes.class);

        }
        public final ApiRequests<CharacterResources> characterResources;
        public final ApiRequests<Episodes> episodes;
    }

    public static Api instance() {
        return new Api();
    }


    public T getRequest(String operation) throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(operation);
            request.setHeader("Accept", "application/json");

            return client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), valueType)
            );
        }
    }

    public T postRequest(String operation) throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(operation);
            request.setHeader("Accept", "application/json");

            return client.execute(request, httpResponse -> {
                        String bodyResponse = EntityUtils.toString(httpResponse.getEntity());
                        JsonNode jsonNode = mapper.valueToTree(bodyResponse);
                        return mapper.readValue(jsonNode.textValue(), valueType);
                    }
            );
        }
    }

    public T postRequest(String operation, String jsonBody) throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(operation);
            request.setHeader("Accept", "application/json");

            StringEntity entity = new StringEntity(jsonBody);
            request.setEntity(entity);
            return client.execute(request, httpResponse -> {
                        String bodyResponse = EntityUtils.toString(httpResponse.getEntity());
                        JsonNode jsonNode = mapper.valueToTree(bodyResponse);
                        return mapper.readValue(jsonNode.textValue(), valueType);
                    }
            );
        }
    }
}
