package com.philo.config

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class AuthFilter : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        // Your custom filtering logic here
        // For example, modify the request
        val builder: ServerHttpRequest.Builder = exchange.request.mutate()
        // ... add your modifications ...

        // Continue the filter chain
        return chain.filter(exchange.mutate().request(builder.build()).build())
    }}