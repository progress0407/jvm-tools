package io.philo.app

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/test")
@RestController
class TestController {

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): String {
        return "User $id"
    }
}