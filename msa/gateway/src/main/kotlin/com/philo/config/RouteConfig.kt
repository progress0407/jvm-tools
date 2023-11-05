package com.philo.config

import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(private val loggingFilter: LoggingFilter) {

    @Bean
    fun routes(builder: RouteLocatorBuilder, loggingFilter: LoggingFilter): RouteLocator {
        return builder.routes()
            .route { it.simpleRoute("micro-service-1", "/ms-1") }
            .route { it.simpleRoute("micro-service-2", "/ms-2") }
            .build()
    }

    private fun PredicateSpec.simpleRoute(serviceName: String, url: String): Buildable<Route> =
        this.path("$url/**")
            .filters { filter ->
                filter.removeRequestHeader("Cookie")
                    .rewritePath("$url(?<segment>/?.*)", "$\\{segment}") // ex. /order/1 -> /1
                    .filter(loggingFilter)
            }
            .uri("lb://${serviceName}")
}