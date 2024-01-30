package io.philo.springevent.app

import io.philo.springevent.app.pre.PreDomainRequestDto
import io.philo.springevent.app.pre.PreDomainService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SpringAppController(
    private val preDomainService: PreDomainService
) {

    @PostMapping("/generate-event")
    fun generateEvent(@RequestBody request: PreDomainRequestDto) {

        preDomainService.execute(request.id, request.percent)
    }
}