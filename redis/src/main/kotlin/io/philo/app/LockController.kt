package io.philo.app

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 오프라인 선점 락
 */
@RestController
@RequestMapping("/test/redis")
class LockController(private val lockService: LockService) {

    @RequestMapping("/proceed-payment/{txId}")
    fun doSomething(@PathVariable txId: String): Any {

        return lockService.doPaymentProcess(txId)
    }

    @RequestMapping("/proceed-payment-2/{txId}")
    fun doSomething2(@PathVariable txId: String): Any {

        return lockService.doPaymentProcess(txId)
    }

}