package philo.mjcoding.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.support.AbstractPlatformTransactionManager

@Configuration
class HelloJobCodingConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: AbstractPlatformTransactionManager
) {

    @Bean
    fun helloWorldJob(): Job {
        return JobBuilder("helloWorldJob", jobRepository)
            .start(helloWorldStep())
            .build()
    }

    @StepScope
    @Bean
    fun helloWorldStep(): Step {
        return StepBuilder("helloWorldStep", jobRepository)
            .tasklet(helloWorldStepTasklet(), transactionManager)
            .build()
    }

    @StepScope
    @Bean
    fun helloWorldStepTasklet(): Tasklet {
        return Tasklet { _, _ ->
            println("hello world spring batch")
            RepeatStatus.FINISHED
        }
    }
}