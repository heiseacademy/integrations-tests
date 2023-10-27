package com.github.sparsick.heise.integration.testcontainers;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonRepositoryTest {


    private PersonRepository repositoryUnderTest;

    @BeforeEach
    void setup(){
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:h2:mem:test_mem;INIT=runscript from 'src/main/resources/database/init.sql'");
        DataSource dataSource = new HikariDataSource(configuration);
        repositoryUnderTest = new PersonRepository(dataSource);
    }

    @Test
    void aroundTrip() {
        var person = new Person();
        person.setFirstName("Alice");
        person.setLastName("Bob");
        person.setJobTitle("Dev");
        repositoryUnderTest.save(person);

        List<Person> allPersons = repositoryUnderTest.findAllPersons();

        assertTrue(allPersons.size() == 1);
    }
}
