package com.philo

import io.philo.config.AuthFilter
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod

@TestConfiguration
class TestRouteConfig {

    @Bean("testRoute")
    fun testRoute(builder: RouteLocatorBuilder,
                  authFilter: AuthFilter
    ): RouteLocator {

        return builder.routes()
            .route {
                it.path("/items/**")
                    .and().method(HttpMethod.GET, HttpMethod.POST)
                    .filters { f -> f.filter(authFilter) }
                    .uri("lb://ITEM-SERVICE")
            }
            .route {
                it.path("/orders/**")
                    .filters { it.removeRequestHeader("Cookie") }
                    .uri("lb://ORDER-SERVICE")
            }
            .route {
                it.path("/users/**")
                    .filters { it.removeRequestHeader("Cookie") }
                    .uri("lb://USER-SERVICE")
            }
            .build()
    }
}
