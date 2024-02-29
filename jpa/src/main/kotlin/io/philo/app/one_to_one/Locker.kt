package io.philo.app.one_to_one

import jakarta.persistence.*

@Entity
class Locker (name: String, member: Member){
    @Id
    @GeneratedValue
    @Column(name = "LOCKED_ID")
    var id: Long? = null
        protected set
    var name: String = name
        protected set

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    var member: Member = member

    protected constructor() : this("default", Member(""))
}
