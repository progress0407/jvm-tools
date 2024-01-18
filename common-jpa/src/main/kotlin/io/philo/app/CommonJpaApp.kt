package io.philo.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommonJpaApp

fun main() {
    runApplication<CommonJpaApp>()
}