package philo.mjcoding.job

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.support.AbstractPlatformTransactionManager

@Configuration
//@EnableBatchProcessing
class HelloJobCodingConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: AbstractPlatformTransactionManager
) {

    private val log = KotlinLogging.logger { }

    // --job.name=helloWorldJob requestDate=20240424
    @Bean
    fun helloWorldJob(helloWorldStep: Step): Job {

        log.info(">>>> hello world spring batch job !!")

        return JobBuilder("helloWorldJob", jobRepository)
            .start(helloWorldStep)
            .build()
    }

    @JobScope
    @Bean
    fun helloWorldStep(
        helloWorldStepTasklet: Tasklet

    ): Step {

        log.info(">>>> this is helloWorldStep !!")


        return StepBuilder("helloWorldStep", jobRepository)
            .tasklet(helloWorldStepTasklet, transactionManager)
            .build()
    }

    @StepScope
    @Bean
    fun helloWorldStepTasklet(
        @Value("#{jobParameters['requestDate']}") requestDate: String?
    ): Tasklet {

        log.info(">>>> hello world spring batch tasklet !!")
        log.info { ">>>> input data, requestDate: $requestDate" }

        return Tasklet { _, _ ->
            RepeatStatus.FINISHED
        }
    }
}
