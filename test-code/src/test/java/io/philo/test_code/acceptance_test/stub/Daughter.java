package io.philo.test_code.acceptance_test.stub;

import io.philo.test_code.app.bean.Child;

public class Daughter implements Child {

    public static final String INFO = "Daughter Class";

    @Override
    public String info() {
        return INFO;
    }
}
