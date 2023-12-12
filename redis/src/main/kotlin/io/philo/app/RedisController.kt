package io.philo.app

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test/redis")
class RedisController(private val redisRepository: RefreshTokenRepository) {

    @GetMapping("/set-key")
    fun _1() {
        val entity = RefreshToken("some-token", 5)
        redisRepository.save(entity)
    }

    @GetMapping("/get-key")
    fun _2() {
        val foundAll = redisRepository.findAll()

        println("foundAll = $foundAll")
    }
}