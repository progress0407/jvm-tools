package com.philo

import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator

@SpringBootTest
class GatewayAppTest {

    @Autowired
    lateinit var routeLocator: RouteLocator

    @Test
    fun `hello`() {

        val routes: MutableList<Route> = routeLocator.routes.collectList().block()!!

        val itemRoute = routes.find { it.msId == "ITEM-SERVICE" }!!

        itemRoute.predicate.toString() shouldContain "/items"

    }
}

private val Route.msId: String
    get() =  this.uri.toString().replaceFirst("lb://", "")


