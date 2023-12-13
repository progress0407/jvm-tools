package io.philo.app

/**
 * Lock을 관리하는 역할
 */
interface LockManager {

    /**
     * Lock 선점
     */
    fun preemptLock(lockId: String)

    /**
     * Lock 보유 확인
     */
    fun checkLock(lockId: String): Boolean

    /**
     * 락 방출
     */
    fun releaseLock(lockId: String)

    /**
     * 기존 락 보유시간 연장
     */
    fun extendLockExpiration(lockId: String, extendSeconds: Int)
}