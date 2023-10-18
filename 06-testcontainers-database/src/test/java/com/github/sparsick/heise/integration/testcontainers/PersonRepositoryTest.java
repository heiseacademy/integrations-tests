package com.github.sparsick.heise.integration.testcontainers;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class PersonRepositoryTest {

    @Container
    private PostgreSQLContainer database = new PostgreSQLContainer(DockerImageName.parse("postgres"));
    private PersonRepository repositoryUnderTest;

    @BeforeEach
    void setup(){
        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(database, ""), "database/init.sql");
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl(database.getJdbcUrl());
        configuration.setUsername(database.getUsername());
        configuration.setPassword(database.getPassword());
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
