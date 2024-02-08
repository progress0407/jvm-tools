package io.philo.app

import mu.KotlinLogging
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.client.*
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.util.function.Supplier


private const val OTHER_SERVER_URL = "http://localhost:8082/listen"

@Component
class ClientScheduler {

    private val log = KotlinLogging.logger { }

    private lateinit var restTemplate: RestTemplate

    init {

        val timeOut = Duration.ofSeconds(1_200)
        restTemplate = RestTemplateBuilder()
            .setConnectTimeout(timeOut)
            .setReadTimeout(timeOut)
            .additionalInterceptors(LoggingInterceptor())
            .requestFactory(Supplier { BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()) })
            .build()
    }

    @Scheduled(fixedDelay = 1_000)
    fun caller() {

        val result = restTemplate.getForEntity(OTHER_SERVER_URL, String::class.java);

        log.info { "something doing -- status: ${result.statusCode}, return value: ${result.body}" }
    }
}
