package io.philo.springevent.app.pre

import jakarta.persistence.*

@Entity
class PreDomainEntity(

    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(name = "some_value", nullable = false)
    var value: Int = 0,
) {
    constructor(value: Int)
            : this(id = null, value = value)

    fun updateValueByPercent(percent: Int) {

        this.value += (value * (percent / 100.0)).toInt()
    }
}