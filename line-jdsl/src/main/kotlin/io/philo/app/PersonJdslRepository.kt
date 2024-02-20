package io.philo.app

import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import io.philo.entity.PersonEntity
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component

@Component
class PersonJdslRepository(
    private val jpqlContext: JpqlRenderContext,
    private val jpqlRenderer: JpqlRenderer,
    private val entityManager: EntityManager,
) {

    fun findBy(id: Long): MutableList<PersonEntity> {

        val query = jpql {
            select(path(PersonEntity::name))
                .from(entity(PersonEntity::class))
                .where(path(PersonEntity::id).eq(id))
        }

        val jpqlRendered = jpqlRenderer.render(query, jpqlContext)

        val resultList: MutableList<PersonEntity> = entityManager.createQuery(jpqlRendered.query, PersonEntity::class.java)
            .apply { jpqlRendered.params.forEach { (name, value) -> entityManager.setProperty(name, value) } }
            .resultList

        return resultList
    }
}