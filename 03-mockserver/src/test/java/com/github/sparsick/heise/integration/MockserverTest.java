package com.github.sparsick.heise.integration;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockserverTest {

    @Test
    void starwarsClient() {
        StarWarsClient clientUnderTest = new StarWarsClient("https://swapi.dev/");

        List<Starship> allStarships = clientUnderTest.findAllStarships();

        assertTrue(allStarships.size() > 0);

    }



}
