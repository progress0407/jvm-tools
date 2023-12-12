package com.philo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.gateway.route.RouteLocator

@SpringBootTest
class GatewayAppTest {

    @Autowired
    lateinit var routeLocator: RouteLocator

    @Test
    fun `hello`() {
        val routes = routeLocator.routes
        println("routes = ${routes}")
    }
}
