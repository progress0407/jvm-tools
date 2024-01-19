package io.philo.spring_reactive.app

import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import java.lang.Thread.sleep
import java.time.Duration

@RestController
class CustomerController(private val customerRepository: CustomerRepository) {

    private val sink: Sinks.Many<Customer> = Sinks.many().multicast().onBackpressureBuffer()

    @GetMapping("/flux")
    fun flux(): Flux<Int> {
        return Flux.just(1, 2, 3, 4, 5)
    }

    @GetMapping("/customers")
    fun findAll(): Flux<Customer?> {
        val datas = customerRepository.findAll()
        sleep(1000)
        return datas.log()
    }

    @GetMapping("/customer/{id}")
    fun findById(@PathVariable id: Long): Mono<Customer?> {
        val found = customerRepository.findById(id).log()
        return found
    }


    @GetMapping(value = arrayOf("/stream"), produces = arrayOf(MediaType.APPLICATION_STREAM_JSON_VALUE))
    fun stream(): Flux<Int> {
        return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log()
    }

    @GetMapping(value = arrayOf("/customer/sse"), produces = arrayOf(MediaType.APPLICATION_STREAM_JSON_VALUE))
    fun findAll_sse(): Flux<Customer?> {
        return customerRepository.findAll().delayElements(Duration.ofSeconds(1)).log()
    }

    @GetMapping("/sink/sse")
    fun sink_findAll(): Flux<ServerSentEvent<Customer>> {

        val flux: Flux<ServerSentEvent<Customer>> = sink.asFlux().map { c -> ServerSentEvent.builder(c).build() }
        return flux
    }

    @GetMapping("/sink/add")
    fun sink_add(): Mono<Customer> {

        return customerRepository.save(Customer("guildong"))
            .doOnNext { customer ->
                sink.tryEmitNext(customer)
            }
    }
}
