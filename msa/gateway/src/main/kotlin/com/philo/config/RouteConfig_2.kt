package com.philo.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig_2 {

    @Bean
    fun routes(builder: RouteLocatorBuilder, loggingFilter: LoggingFilter): RouteLocator {

        return builder.routes()
            .route {
                it.path("/items/**")
                    .filters { it.removeRequestHeader("Cookie") }
                    .uri("lb://ITEM-SERVICE")
            }
            .route {
                it.path("/orders/**")
                    .filters { it.removeRequestHeader("Cookie") }
                    .uri("lb://${"ORDER-SERVICE"}")
            }
            .route {
                it.path("/users/**")
                    .filters { it.removeRequestHeader("Cookie") }
                    .uri("lb://${"USER-SERVICE"}")
            }
            .build()
    }
}
