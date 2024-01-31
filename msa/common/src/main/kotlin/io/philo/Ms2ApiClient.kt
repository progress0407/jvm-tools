package io.philo

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("micro-service-2")
interface Ms2ApiClient {

    @GetMapping("/ms2/internal/some-api")
    fun someApi() : SomeApiDto
}