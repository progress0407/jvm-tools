package philo.mjcoding.shedlock

import net.javacrumbs.shedlock.core.LockProvider
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource


@Configuration
class SchedulerConfiguration {

    @Bean
    fun lockProvider(dataSource: DataSource): LockProvider {

        return JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder()
                    .withJdbcTemplate(JdbcTemplate(dataSource))
                    .usingDbTime()
                    .build()
                )
    }
}
