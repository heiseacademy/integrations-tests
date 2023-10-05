package com.github.sparsick.heise.integration;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(MockServerExtension.class)
@Tag("verifyFake")
public class MockserverTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(MockserverTest.class);

    private final MockServerClient mockServerClient;
    private String starshipTestData;
    private StarWarsClient clientUnderTest;

    public MockserverTest(MockServerClient mockServerClient) throws IOException {
        this.mockServerClient = mockServerClient;
        this.starshipTestData = IOUtils.toString(MockserverTest.class.getClassLoader().getResourceAsStream("starship.json"), Charset.defaultCharset());
    }

    @BeforeEach
    void setUp() {
        String baseUrl = System.getProperty("swapi.base.url","http://localhost:" + mockServerClient.remoteAddress().getPort());
        LOGGER.info("Base URL: " + baseUrl);
        clientUnderTest = new StarWarsClient(baseUrl);

    }

    @Test
    void starwarsClient() {

        mockServerClient.when(request().withMethod("GET").withPath("/api/starships"))
                .respond(HttpResponse.response().withBody(starshipTestData));

        List<Starship> allStarships = clientUnderTest.findAllStarships();

        assertTrue(allStarships.size() > 0);

    }



}
