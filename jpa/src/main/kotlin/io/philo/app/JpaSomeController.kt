package io.philo.app

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

@RequestMapping("/jpa-some/test")
@RestController
@Transactional
class JpaSomeController(private val jpaSomeRepository: JpaSomeRepository) {

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

}