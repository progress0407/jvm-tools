package io.philo.test_code

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldNotBeBlank
import org.testcontainers.containers.PostgreSQLContainer


class TestContainerTestKt : StringSpec({

    val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:latest")

    "테스트 컨테이너 구동" {
        postgresContainer.start()

        val jdbcUrl = postgresContainer.getJdbcUrl()
        val username = postgresContainer.username
        val password = postgresContainer.password

        jdbcUrl.shouldNotBeBlank()
        username.shouldNotBeBlank()
        password.shouldNotBeBlank()

        postgresContainer.stop()
    }
})