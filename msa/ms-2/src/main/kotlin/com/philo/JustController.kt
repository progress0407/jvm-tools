package com.philo

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JustController {

    private val log = KotlinLogging.logger { }

    @GetMapping("/just")
    fun just() {
        log.info { "" }
    }
}