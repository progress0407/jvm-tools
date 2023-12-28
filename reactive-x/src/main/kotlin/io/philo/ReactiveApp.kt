package io.philo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveApp

fun main() {
    runApplication<ReactiveApp>()
}