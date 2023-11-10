package com.philo

import mu.KotlinLogging
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JustController(val discoveryClient: DiscoveryClient) {

    private val log = KotlinLogging.logger { }

    @GetMapping("/ms-1/just/has-prefix")
    fun justWithMsPrefix(): String {
        log.info { "" }
        return "ok"
    }

    @GetMapping("/just/has-not-prefix")
    fun just(): String {
        log.info { "" }
        return "ok"
    }

    @GetMapping("/test/instances")
    fun instances(): MutableList<ServiceInstance>? {
        return discoveryClient.getInstances("micro-service-1");
    }

    @GetMapping("/test/instances-2")
    fun instances2(): Map<String, List<String>> {
        return discoveryClient.services.associateWith { serviceId ->
            discoveryClient.getInstances(serviceId).map {
                it.uri.toString()
            }
        }
    }

    @GetMapping("/test/instances-3")
    fun instances3(): Map<String, MutableList<ServiceInstance>> {
        return discoveryClient.services.associateWith { serviceId ->
            discoveryClient.getInstances(serviceId)
        }
    }
}