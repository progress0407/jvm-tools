package io.philo.app

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LockService(private val lockManager: LockManager) {

    @Transactional
    fun doPaymentProcess(txId: String): Any {
        if(lockManager.checkLock(txId)) {
            return "이미 결제 처리 중입니다."
        }

        lockManager.preemptLock(txId)

        Thread.sleep(6_000)

        lockManager.releaseLock(txId)

        return "결제 처리 완료 및 락 해제"
    }
}