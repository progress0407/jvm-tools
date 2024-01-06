package com.philo

import com.philo.config.LoggingFilter
import io.kotest.matchers.collections.shouldContain
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

    @Autowired
    lateinit var loggingFilter: LoggingFilter

    @Test
    fun `hello`() {

        val routes: MutableList<Route> = routeLocator.routes.collectList().block()!!


        val itemRoute = routes.find { it.msId == "ITEM-SERVICE" }!!
        itemRoute.predicate.toString() shouldContain "/items"
        itemRoute.filters shouldContain  loggingFilter

        val orderRoute = routes.find { it.msId == "ORDER-SERVICE" }!!
        orderRoute.predicate.toString() shouldContain "/orders"
        orderRoute.filters shouldContain loggingFilter
    }
}


private val Route.msId: String
    get() =  this.uri.toString().replaceFirst("lb://", "")


