package io.philo.springevent.app

import io.philo.springevent.app.pre.PreDomainEntity
import io.philo.springevent.app.pre.PreDomainRepository
import io.philo.springevent.app.pre.PreDomainRequestDto
import io.philo.springevent.app.pre.PreDomainService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Executors


@RestController
class SpringAppController(
    private val preDomainService: PreDomainService,
    private val preDomainRepository: PreDomainRepository,
) {

    @PostMapping("/generate-event")
    fun generateEvent(@RequestBody request: PreDomainRequestDto) {

        preDomainService.execute(request.id, request.percent, request.ex)
    }

    @GetMapping("/events")
    fun getAllEntities(): List<PreDomainEntity> {

        return preDomainRepository.findAll()
    }

    @GetMapping("/gen-threads")
    fun genThreads(): Unit {

        for (i in 1..1_0000) {
            println("count = $i")
            val thread = Thread {
                try {
                    println("hello hi~")
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            thread.start()
        }
        println("thread generated!")
    }
}