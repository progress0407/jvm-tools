package io.philo.test_code.acceptance_test.stub;

import io.philo.test_code.app.bean.Child;
import org.springframework.context.annotation.Bean;

public class TestStubBeanConfig {

    @Bean
    public Child child() {
        return new Daughter();
    }
}
