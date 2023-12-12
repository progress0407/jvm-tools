package io.philo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisApp {
}

fun main(args: Array<String>) {
    runApplication<RedisApp>(*args)
}