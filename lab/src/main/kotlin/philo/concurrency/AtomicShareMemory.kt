package philo.concurrency

import java.util.concurrent.atomic.AtomicInteger

class AtomicShareMemory {
    var count  = AtomicInteger(0)
}