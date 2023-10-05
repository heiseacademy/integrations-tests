package com.github.sparsick.heise.integration;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.HttpResponse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(MockServerExtension.class)
public class MockserverTest {

    private final MockServerClient mockServerClient;
    private String starshipTestData;

    public MockserverTest(MockServerClient mockServerClient) throws IOException {
        this.mockServerClient = mockServerClient;
        this.starshipTestData = IOUtils.toString(MockserverTest.class.getClassLoader().getResourceAsStream("starship.json"), Charset.defaultCharset());
    }

    @Test
    void starwarsClient() {
        StarWarsClient clientUnderTest = new StarWarsClient("http://localhost:" + mockServerClient.remoteAddress().getPort());

        mockServerClient.when(request().withMethod("GET").withPath("/api/starships"))
                .respond(HttpResponse.response().withBody(starshipTestData));

        List<Starship> allStarships = clientUnderTest.findAllStarships();

        assertTrue(allStarships.size() > 0);

    }



}
