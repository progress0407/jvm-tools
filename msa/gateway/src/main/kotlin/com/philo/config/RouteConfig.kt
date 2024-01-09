package com.philo.config

import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(private val loggingFilter: GlobalLoggingFilter) {

    @Bean
    fun routes(builder: RouteLocatorBuilder, loggingFilter: GlobalLoggingFilter): RouteLocator {
        return builder.routes()
            .route { it.simpleRoute("micro-service-1", "/ms-1") }
            .route { it.simpleRoute("micro-service-2", "/ms-2") }
            .build()
    }

    private fun PredicateSpec.simpleRoute(serviceName: String, path: String): Buildable<Route> =
        this.path("$path/**")
            .filters { filter ->
                filter.removeRequestHeader("Cookie")
                    .rewritePath("$path(?<segment>/?.*)", "$\\{segment}") // ex. /order/1 -> /1
                    .filter(loggingFilter)
            }
            .uri("lb://${serviceName}")
}