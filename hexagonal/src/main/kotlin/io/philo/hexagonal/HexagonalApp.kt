package io.philo.hexagonal

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HexagonalApp

fun main() {
    runApplication<HexagonalApp>()
}