package io.philo.app

interface LockManager {

    fun tryLock(lockId: String)
    fun checkLock(lockId: String): Boolean
    fun releaseLock(lockId: String)
    fun extendLockExpiration(lockId: String, extendSeconds: Int)
}