package io.philo.app

import org.springframework.data.repository.CrudRepository
import java.util.*

interface ExtendedCrudRepository<T, ID>: CrudRepository<T, ID> {

    fun findByIdOrNull(id: ID): T? {

        val optionalEntity: Optional<T> = findById(id)

        return optionalEntity.orElse(null)
//        return findById(id).getOrNull()
    }
}