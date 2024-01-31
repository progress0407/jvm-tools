package com.philo

import io.philo.config.AuthFilter
import io.philo.config.GlobalLoggingFilter
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.*
import kotlin.properties.Delegates
import kotlin.reflect.KClass

@SpringBootTest
@Import(TestRouteConfig2::class)
class GatewayAppTestByDsl2 {

    @Autowired
    @Qualifier("testRoute")
    lateinit var routeLocator: RouteLocator

    @Autowired
    lateinit var loggingFilter: GlobalLoggingFilter

    var routes: MutableList<Route>? = null


    @PostConstruct
    fun init() {
        routes = routes()
    }

    @Test
    fun `상품 서비스의 route 목록을 검증한다`() {

        routes = routes()

        routes!!["ITEM-SERVICE"] shouldBeRoute {
            path("/items")
            httpMethods(GET)
        }

        routes!!["ITEM-SERVICE"] shouldBeRoute {
            path("/items")
            httpMethods(POST, PUT, DELETE)
            filter<AuthFilter>()
        }
    }

    @Test
    fun `주문 서비스의 route 목록을 검증한다`() {

        routes = routes()

        routes!!["ORDER-SERVICE"] shouldBeRoute {
            path("/orders")
            httpMethods(GET)
        }

        routes!!["ORDER-SERVICE"] shouldBeRoute {
            path("/orders")
            httpMethods(POST)
            filter<AuthFilter>()
        }
    }

    @Disabled
    @Test
    fun `route 목록을 검증한다 (filters)`() {

        val routes = routes()

        routes["ITEM-SERVICE"] shouldBeRoute {
            path("/items")
            httpMethods(GET, POST)
            filters(AuthFilter::class)
        }
    }

    private fun routes() = routeLocator.routes.collectList().block()!!


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

        inline fun <reified T : GatewayFilter> filter() {
            this.filters = arrayOf(T::class)
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


    private operator fun <T : Route> MutableList<T>.get(msId: String): T =
        this.find { it.msId == msId }!!
}
