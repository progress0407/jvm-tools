package com.philo.config

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange


@Component
class AuthFilter : AbstractGatewayFilterFactory<AuthFilter.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        // grab configuration from Config object
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            //If you want to build a "pre" filter you need to manipulate the
            //request before calling chain.filter
            val builder: ServerHttpRequest.Builder = exchange.request.mutate()
            chain.filter(exchange.mutate().request(builder.build()).build())
        }
    }

    class Config { //Put the configuration properties for your filter here
    }
}