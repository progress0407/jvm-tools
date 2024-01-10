package io.philo.test_code.test_container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestContainerTest {

    PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

    @BeforeEach
    void setUp() {
        postgresContainer.start();
    }

    @AfterEach
    void tearDown() {
        postgresContainer.stop();
    }

    @Test
    void testPostgresContainer() {
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();

        assertFalse(jdbcUrl.isBlank());
        assertFalse(username.isBlank());
        assertFalse(password.isBlank());
    }
}
