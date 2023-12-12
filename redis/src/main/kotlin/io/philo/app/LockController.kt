package io.philo.app

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 오프라인 선점 락
 */
@RestController
@RequestMapping("/test/redis")
class LockController(private val lockManager: LockManager) {

    @RequestMapping("/lock-tx/{txId}")
    fun lockTx(@PathVariable txId: String) {

//        lockManager.checkLock(txId)

        lockManager.tryLock(txId)
    }

    @RequestMapping("/command-tx/{txId}")
    fun commandTx(@PathVariable txId: String) {

        if(lockManager.checkLock(txId)) {
            throw LockException("")
        }
    }

    @RequestMapping("/proceed-payment/{txId}")
    fun doSomething(txId: String) {

        if(lockManager.checkLock(txId)) {
            throw LockException("이미 결제 처리중")
        }

        lockManager.tryLock(txId)

        Thread.sleep(6_000)

        lockManager.releaseLock(txId)
    }
}