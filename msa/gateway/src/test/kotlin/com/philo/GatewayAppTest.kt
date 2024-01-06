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
import kotlin.reflect.KClass
import com.philo.GatewayAppTest.TestRoute as Route_

@SpringBootTest
class GatewayAppTest {
    @Autowired
    lateinit var routeLocator: RouteLocator

    @Autowired
    lateinit var loggingFilter: LoggingFilter

    @Test
    fun `route 목록을 검증한다`() {

        val routes: MutableList<Route> = routeLocator.routes.collectList().block()!!

        routes["ITEM-SERVICE"] shouldBe_ Route_("/item", AuthFilter::class)

//        val orderRoute = routes.find { it.msId == "ORDER-SERVICE" }!!
//        orderRoute.predicate.toString() shouldContain "/orders"
//        orderRoute.filters shouldContain loggingFilter
    }

    final inline infix fun <reified F: GatewayFilter> Route.shouldBe_(route: Route_<F>) {
        this.predicate.toString() shouldContain route.path
        if(route.filter != null)
            this.filters shouldHasFilter route.filter!!
    }

    final inline infix fun <T : List<GatewayFilter>, reified U : GatewayFilter>
            T.shouldHasFilter(expected: KClass<U>) {

        val isMatched = this.map { gatewayFilter -> gatewayFilter as OrderedGatewayFilter }
            .any { orderedGatewayFilter -> orderedGatewayFilter.delegate is U }

        isMatched shouldBe true
    }

    private val Route.msId: String
        get() = this.uri.toString().replaceFirst("lb://", "")

    class TestRoute<F: GatewayFilter>(var path: String, var filter: KClass<F>?) {

    }

    private operator fun <T: Route> MutableList<T>.get(msId: String): T {
        return this.find { it.msId == msId }!!
    }
}


