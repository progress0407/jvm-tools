package io.philo.app

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletResponse

class ReactiveFilter2(private val eventNotify: EventNotify) : Filter {

    override fun doFilter(
        servletRequest: ServletRequest,
        servletResponse: ServletResponse,
        chain: FilterChain
    ) {

        val httpServletResponse = servletResponse as HttpServletResponse
        httpServletResponse.contentType = "text/event-stream;charset=utf-8"

        val out = httpServletResponse.writer

        out.println("필터 2 실행")

        eventNotify.add("새로운 데이터")

        while (true) {
            if (eventNotify.change) {
                val lastEvent = eventNotify.events.last()
                out.println("필터 2 실행 $lastEvent")
                out.flush()
                eventNotify.change = false
            }
        }
    }
}