package io.philo.app


interface LockRepository : ExtendedCrudRepository<Lock, String> {

/*
    fun findByIdOrNull(id: String): Lock? {
        val bb: Optional<Lock> = findById(id)
        return bb.getOrNull()
    }
*/
}