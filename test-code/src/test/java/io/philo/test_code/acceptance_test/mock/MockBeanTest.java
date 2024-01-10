package io.philo.test_code.acceptance_test.mock;

import io.philo.test_code.acceptance_test.AcceptanceTest;
import io.philo.test_code.app.bean.Child;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class MockBeanTest extends AcceptanceTest {


    @MockBean
    Child child;


    @DisplayName("Mock Bean이 등록됨을 확인한다")
    @Test
    void assert_mock_bean() {

        given(child.info()).willReturn("This is Mocked");

        String responseBody = get("/person/mock").asString();

        assertThat(responseBody).isEqualTo("This is Mocked");
    }
}
