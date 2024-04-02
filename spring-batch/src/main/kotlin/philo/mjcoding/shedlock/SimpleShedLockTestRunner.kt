package philo.mjcoding.shedlock

import mu.KotlinLogging
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

//@Component
class SimpleShedLockTestRunner {

    private val log = KotlinLogging.logger { }

    @Scheduled(cron = "*/5 * * * * ?")
    @SchedulerLock(
        name = "TaskScheduler_scheduledTask",
        lockAtLeastFor = "PT5M",
        lockAtMostFor = "PT14M"
    )
    fun run() {
        log.info { "run " }
    }
}