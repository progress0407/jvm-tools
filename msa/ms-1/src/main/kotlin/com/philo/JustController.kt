package com.philo

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JustController {

    private val log = KotlinLogging.logger { }

    @GetMapping("/ms-1/just")
    fun justWithMsPrefix(): String {
        log.info { "" }
        return "ok"
    }

    @GetMapping("/just")
    fun just(): String {
        log.info { "" }
        return "ok"
    }
}