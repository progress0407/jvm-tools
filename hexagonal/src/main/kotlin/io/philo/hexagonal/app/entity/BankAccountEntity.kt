package io.philo.hexagonal.app.entity

import jakarta.persistence.*
import java.math.BigDecimal


@Entity
@Table(name = "account")
class BankAccountEntity(

    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column
    var balance: BigDecimal = BigDecimal.valueOf(0)
) {

    constructor(balance: BigDecimal) : this(null, balance)
}