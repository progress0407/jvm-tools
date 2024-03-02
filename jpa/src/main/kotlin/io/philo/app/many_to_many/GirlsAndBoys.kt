package io.philo.app.many_to_many

import jakarta.persistence.*

@Entity
class GirlsAndBoys protected constructor(id: Long? = null, girl: Girl? = null, boy: Boy? = null) {
    @Id
    @GeneratedValue
    var id: Long? = id
        protected set

    @ManyToOne
    @JoinColumn(name = "girl_id")
    private var girl: Girl? = girl

    @ManyToOne
    @JoinColumn(name = "boy_id")
    private var boy: Boy? = boy

    constructor(girl: Girl, boy: Boy) : this(null, girl!!, boy!!)

    constructor() : this(null, null)
}
