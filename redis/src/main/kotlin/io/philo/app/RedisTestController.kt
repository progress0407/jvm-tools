package io.philo.app

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test/redis")
class RedisTestController(private val redisRepository: LockRepository) {

    @GetMapping("/set-key")
    fun _1() {

        val entity = Lock("foo", 5)

        redisRepository.save(entity)
    }

    @GetMapping("/get-key")
    fun _2(): Any {

        val foundAll: MutableIterable<Lock> = redisRepository.findAll()

        println("foundAll = $foundAll")

        return foundAll
    }

    @GetMapping("/del-key")
    fun _3() {

        redisRepository.deleteAll()
    }
}