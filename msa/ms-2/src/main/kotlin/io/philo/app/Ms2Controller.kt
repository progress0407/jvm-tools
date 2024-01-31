package io.philo.app

import io.philo.SomeApiDto
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Ms2Controller {

    private val log = KotlinLogging.logger { }

    @GetMapping("/just")
    fun just() {
        log.info { "" }
    }

    @GetMapping("/ms2/internal/some-api")
    fun someApi(): SomeApiDto {

        return SomeApiDto("ms2 message")
    }
}

