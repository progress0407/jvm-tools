package com.philo

import io.philo.config.AuthFilter
import io.philo.config.GlobalLoggingFilter
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import kotlin.reflect.KClass
import com.philo.GatewayAppTest.TestRoute as Route_

@SpringBootTest
@Import(TestRouteConfig::class)
class GatewayAppTest {

    @Autowired
    lateinit var routeLocator: RouteLocator

    @Autowired
    lateinit var loggingFilter: GlobalLoggingFilter


    @Test
    fun `route 목록을 검증한다`() {

        val routes: MutableList<Route> = routeLocator.routes.collectList().block()!!

        routes["ITEM-SERVICE"] shouldBe_ Route_("/item", listOf(GET, POST), AuthFilter::class)
        routes["ORDER-SERVICE"] shouldBe_ Route_("/orders")
    }

    final inline infix fun <reified F : GatewayFilter> Route.shouldBe_(route: Route_<F>) {

        // URL 검증
        this.predicate.toString() shouldContain route.path
        if (route.httpMethods != null) {
            for (httpMethod in route.httpMethods!!) {
                this.predicate.toString() shouldContain httpMethod.toString()
            }
        }

        // 필터 목록 검증
        if (route.filter != null)
            this.filters shouldHasFilter route.filter!!
    }

    final inline infix fun <reified F : GatewayFilter, L : List<GatewayFilter>>
            L.shouldHasFilter(expected: KClass<F>) {

        val isMatched = this.filterIsInstance<OrderedGatewayFilter>()
            .any { orderedGatewayFilter -> orderedGatewayFilter.delegate is F }

        isMatched shouldBe true
    }

    private val Route.msId: String
        get() = this.uri.toString().replaceFirst("lb://", "")

    class TestRoute<F : GatewayFilter>(
        var path: String,
        var httpMethods: List<HttpMethod>? = null,
        var filter: KClass<F>? = null,
    )

    private operator fun <T : Route> MutableList<T>.get(msId: String): T {
        return this.find { it.msId == msId }!!
    }
}


