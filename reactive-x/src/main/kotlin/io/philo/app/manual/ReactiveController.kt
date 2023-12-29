package io.philo.app.manual

import jakarta.servlet.ServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class ReactiveController {

    @GetMapping("/reactive-x")
    fun _1(servletResponse: ServletRequest) {

        val httpServletResponse = servletResponse as HttpServletResponse
        httpServletResponse.contentType = "text/plain;charset=utf-8"
        val out = httpServletResponse.writer

        out.flush()
    }
}