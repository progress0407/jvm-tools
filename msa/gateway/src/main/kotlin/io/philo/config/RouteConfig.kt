package io.philo.config

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.BooleanSpec
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.*

@Configuration
class RouteConfig(private val loggingFilter: GlobalLoggingFilter,
                  private val authFilter: AuthFilter
) {

    @Bean
    fun routes(builder: RouteLocatorBuilder, loggingFilter: GlobalLoggingFilter): RouteLocator {

        return builder.routes()
            .route { it.simpleRoute(serviceName = "micro-service-1", path = "/ms-1", filter = authFilter) }
            .route { it.simpleRoute(serviceName = "micro-service-2", path = "/ms-2", methods = arrayOf(POST, PUT, DELETE)) }
            .build()
    }

    /**
     * todo filters 구현
     */
    private fun PredicateSpec.simpleRoute(serviceName: String, path: String, filter: GatewayFilter? = null, vararg methods: HttpMethod = emptyArray()): Buildable<Route> {

        val pathSpec = this.path("$path/**")

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