package io.philo.springevent.app.post

import jakarta.persistence.*

@Entity
class PostDomainEntity(

    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false)
    var preDomainId: Long? = null,  // FK

    @Column(name = "some_value", nullable = false)
    var value: Int = 0,
) {

    constructor(preDomainId: Long, value: Int)
            : this(id = null, preDomainId = preDomainId, value = value)

    fun updateValueByPercent(percent: Int) {

        this.value += (value * (percent / 100.0)).toInt()
    }
}