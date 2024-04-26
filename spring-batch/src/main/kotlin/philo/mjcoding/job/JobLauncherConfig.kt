package philo.mjcoding.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JobLauncherConfig(
    private val jobLauncher: JobLauncher,
    private val job: Job
) {
    @Bean
    fun runJob(): CommandLineRunner {
        return CommandLineRunner {
            val jobParameters = JobParametersBuilder()
                .addString("requestDate", "20240427")
                .toJobParameters()

            jobLauncher.run(job, jobParameters)
        }
    }
}