package com.philo

import io.philo.config.AuthFilter
import io.philo.config.GlobalLoggingFilter
import jakarta.ws.rs.GET
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.*
import java.lang.IllegalArgumentException

@TestConfiguration
class TestRouteConfig2(private val loggingFilter: GlobalLoggingFilter,
                       private val authFilter: AuthFilter
) {

    @Bean("testRoute")
    fun testRoute(builder: RouteLocatorBuilder, loggingFilter: GlobalLoggingFilter): RouteLocator {

        return builder.routes()
            .route { it.simpleRoute(serviceName = "ITEM-SERVICE", path = "/items", method = GET) }
            .route { it.simpleRoute(serviceName = "ITEM-SERVICE", path = "/items", filter = authFilter, methods = arrayOf(POST, PUT, DELETE)) }
            .route { it.simpleRoute(serviceName = "ORDER-SERVICE", path = "/orders", method = GET) }
            .route { it.simpleRoute(serviceName = "ORDER-SERVICE", path = "/orders", filter = authFilter, methods = arrayOf(POST)) }
            .build()
    }

    /**
     * todo filters 구현
     */
    private fun PredicateSpec.simpleRoute(serviceName: String,
                                          path: String,
                                          filter: GatewayFilter? = null,
                                          method: HttpMethod? = null,
                                          vararg methods: HttpMethod = emptyArray()
    ): Buildable<Route> {

        if(method != null && methods.isNotEmpty())
            throw IllegalArgumentException("method와 methods 둘 모두 값이 있을 수 없습니다.")

        val pathSpec = this.path("$path/**")

        if(method != null)
            pathSpec.and().method(method)

        if (methods.isNotEmpty())
            pathSpec.and().method(*methods)

        return pathSpec
            .filters { filterSpec ->

                filterSpec.removeRequestHeader("Cookie")
                    .rewritePath("$path(?<segment>/?.*)", "$\\{segment}") // ex. /order/1 -> /1
                    .filter(loggingFilter)

                if(filter != null)
                    filterSpec.filters(filter)

                filterSpec
            }
            .uri("lb://${serviceName}")
    }
}
