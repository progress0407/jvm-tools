package io.philo.app

import org.springframework.stereotype.Component

@Component
class RedisLockManager(private val repository: LockRepository) : LockManager {

    override fun tryLock(lockId: String) {

        if (checkLock(lockId)) throw LockException("Lock이 이미 존재합니다.")

        val lock = Lock(lockId, 3)

        repository.save(lock)
    }

    override fun checkLock(lockId: String): Boolean {

        return repository.existsById(lockId)
    }

    override fun releaseLock(lockId: String) {

        if (checkLock(lockId).not()) throw LockException("Lock이 존재하지 않습니다.")

        repository.deleteById(lockId)
    }

    override fun extendLockExpiration(lockId: String, extendSeconds: Int) {

        val lock = repository.findByIdOrNull(lockId) ?: throw LockException("Lock이 존재하지 않습니다.")

        lock.update(extendSeconds)
    }
}