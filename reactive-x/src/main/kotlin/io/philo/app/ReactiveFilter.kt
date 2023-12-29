package io.philo.app

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletResponse

class ReactiveFilter : Filter {

    override fun doFilter(servletRequest: ServletRequest,
                          servletResponse: ServletResponse,
                          chain: FilterChain) {

        val httpServletResponse = servletResponse as HttpServletResponse
//        httpServletResponse.contentType = "text/plain;charset=utf-8"
        httpServletResponse.contentType = "text/event-stream;charset=utf-8"

        val out = httpServletResponse.writer

        for (i in 1..5) {
            out.println("응답 :  $i")
            out.flush()
            Thread.sleep(500)
        }
    }
}