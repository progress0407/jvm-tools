package io.philo.test_code.acceptance_test.stub;

import io.philo.test_code.acceptance_test.AcceptanceTest;
import io.philo.test_code.app.bean.Child;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 아래와 같은 경우에 Test Bean DI가 된다
 * <br>
 * - @Import + @Bean
 * <br>
 * <br>
 * 아래와 같은 경우 Test Bean DI가 안 된다
 * <br>
 * - @TestComponent만 있을 경우
 * <br>
 * - @TestConfiguration만 있을 경우
 */
@Import(TestStubBeanConfig.class)
public class StubBeanTest extends AcceptanceTest {


    @Autowired
    Child child;


    @DisplayName("프로덕션의 Bean이 아닌 Test 패키지의 Bean이 등록됨을 확인한다")
    @Test
    void assert_test_bean() {
        assertThat(child.info()).isEqualTo(Daughter.INFO);
    }
}
