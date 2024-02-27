package io.philo.app

import jakarta.annotation.PostConstruct
import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Table
import org.springframework.stereotype.Component

@Component
class SearchAllEntities {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @PostConstruct
    fun init() {
        val entities = entityManager.metamodel.entities

        val allTableNames =
            entities.map { it.javaType }
                .map { entityMetaInfo ->
                    val tableInfo = entityMetaInfo.getAnnotation(Table::class.java)
                    tableInfo?.name ?: entityMetaInfo.simpleName.toSnakeCase()
                }
                .toList()

        val dmls =
            allTableNames.map { tableName -> "update set some_colum = that from $tableName where id = some_id" }
                .toList()

        dmls.forEach { println(it) }
    }

    fun String.toSnakeCase(): String = this.replace(Regex("(?<!^)([A-Z])"), "_$1").lowercase()
}