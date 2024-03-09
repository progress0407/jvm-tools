package io.philo.app

import io.philo.app.many_to_one.OrderService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime

@RequestMapping("/jpa-some/test")
@RestController
@Transactional
class JpaSomeController(
    private val jpaSomeRepository: JpaSomeRepository,
    private val orderService: OrderService
) {

    @PostMapping
    fun makeEntity() {
        val entity = JpaSomeEntity("hello", listOf("a", "b"))
        jpaSomeRepository.save(entity)
    }

    @GetMapping
    fun getLocalDateTime(): LocalDateTime {

        return LocalDateTime.now()
    }

    @GetMapping("/2")
    fun getLocalDate(): LocalDate {

        return LocalDate.now()
    }

    @GetMapping("/cascade-whole")
    fun cascade_whole(): Map<String, Long?> {
        return orderService.order_whole()
    }

    @GetMapping("/cascade-1")
    fun cascade1(): Map<String, Long?> {
        return orderService.order1()
    }

    @GetMapping("/cascade-2/{orderHistoryId}")
    fun cascade2(@PathVariable orderHistoryId: Long): String {
        return orderService.order2(orderHistoryId)
    }
}