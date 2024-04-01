package philo.mjcoding.shedlock

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class ShedLock(
    @Id
    private val name: String,
    @Column
    private val lockUntil: String,
    @Column
    private val lockedAt: LocalDateTime,
    @Column
    private val lockedBy: String
) {
    constructor() : this("", "", LocalDateTime.now(), "")

}