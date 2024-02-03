package io.philo.app

import com.netflix.discovery.EurekaClient
import io.philo.Ms2ApiClient
import mu.KotlinLogging
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Ms1Controller(
    private val discoveryClient: DiscoveryClient,
    private val ms2ApiClient: Ms2ApiClient,
    private val eurekaClient: EurekaClient
) {

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

    @GetMapping("/ms1/internal/execute")
    fun internalExecute(): String {

        val ms2Dto = ms2ApiClient.someApi()

        return "ms1 message, ${ms2Dto.message}"
    }

    @PostMapping("/test/fetch-registry")
    fun fetchRegistry() {
        refreshRegistry();
    }

    private fun refreshRegistry() {
        val applications = eurekaClient.applications
        println("applications = ${applications}")
        // MICRO-SERVICE-1
        val application = eurekaClient.getApplication("MICRO-SERVICE-1")
        println("application = ${application}")
        val applications1 = eurekaClient.getApplications("http://localhost:8081/")
        println("applications1 = ${applications1}")
    }
}