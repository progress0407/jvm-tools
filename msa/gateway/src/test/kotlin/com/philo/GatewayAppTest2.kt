package com.philo

import com.philo.config.AuthFilter
import com.philo.config.LoggingFilter
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import kotlin.properties.Delegates
import kotlin.reflect.KClass

@SpringBootTest
class GatewayAppTest2 {
    @Autowired
    lateinit var routeLocator: RouteLocator

    @Autowired
    lateinit var loggingFilter: LoggingFilter

    @Test
    fun `route 목록을 검증한다`() {

        val routes: MutableList<Route> = routeLocator.routes.collectList().block()!!

        routes["ITEM-SERVICE"] shouldBeRoute {
            path("/items")
            httpMethods(GET, POST)
            filters(AuthFilter::class)
        }

        routes["ORDER-SERVICE"] shouldBeRoute {
            path("/orders")
        }
    }


    private infix fun
            Route.shouldBeRoute(block: TestRoute.() -> Unit) {

        val route = TestRoute()
        route.block()

        this shouldBe_ route
    }

    private class TestRoute {

        var path: String by Delegates.notNull()
        var httpMethods: Array<out HttpMethod>? = null
        var filters: Array<out KClass<out GatewayFilter>>? = null

        fun path(value: String) {
            this.path = value
        }

        fun httpMethods(vararg values: HttpMethod) {
            this.httpMethods = values
        }

        fun filters(vararg values: KClass<out GatewayFilter>) {
            this.filters = values
        }
    }

    private infix fun Route.shouldBe_(targetRoute: TestRoute) {

        // URL 검증
        predicate.toString() shouldContain targetRoute.path
        if (targetRoute.httpMethods != null) {
            for (httpMethod in targetRoute.httpMethods!!) {
                predicate.toString() shouldContain httpMethod.toString()
            }
        }

        // 필터 목록 검증
        if (targetRoute.filters != null) {
            for (filter in targetRoute.filters!!) {
                this.filters shouldHasFilter filter
            }
        }
    }

    final inline infix fun <reified F : GatewayFilter> List<F>.shouldHasFilter(expected: KClass<out F>) {

        val isMatched = this.filterIsInstance<OrderedGatewayFilter>()
            .any { orderedGatewayFilter -> orderedGatewayFilter.delegate is F }

        isMatched shouldBe true
    }

    private val Route.msId: String
        get() = this.uri.toString().replaceFirst("lb://", "")


    private operator fun <T : Route> MutableList<T>.get(msId: String): T {
        return this.find { it.msId == msId }!!
    }
}


