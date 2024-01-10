package io.philo.test_code.app.bean;

import org.springframework.stereotype.Component;

@Component
public class Son implements Child {

    public static final String INFO = "Son Class";

    @Override
    public String info() {
        return INFO;
    }
}
