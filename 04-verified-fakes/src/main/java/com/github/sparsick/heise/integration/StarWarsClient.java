package com.github.sparsick.heise.integration;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class StarWarsClient {

    private final HttpClient restClient;
    private final String baseUrl;

    public StarWarsClient(String baseUrl) {
        this.restClient = HttpClient.newHttpClient();
        try {
            this.baseUrl = new URL(baseUrl).toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Starship> findAllStarships() {
        List<Starship> starships = new ArrayList<>();
        String nextPageUrl = baseUrl + "/api/starships";
        do {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(nextPageUrl))
                        .GET()
                        .build();

                HttpResponse<String> response = restClient.send(request, HttpResponse.BodyHandlers.ofString());
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

                for (JsonElement result : jsonObject.get("results").getAsJsonArray().asList()) {
                    starships.add(Starship.from(result.getAsJsonObject().toString()));
                }

                if (jsonObject.get("next").isJsonNull()) {
                    nextPageUrl = null;
                } else {
                    nextPageUrl = jsonObject.get("next").getAsString();
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while (nextPageUrl != null);
        return starships;
    }

}
