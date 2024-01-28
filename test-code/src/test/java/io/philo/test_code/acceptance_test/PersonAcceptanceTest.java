package io.philo.test_code.acceptance_test;

import io.philo.test_code.app.PersonRepository;
import io.philo.test_code.app.dto.PersonCreateRequest;
import io.philo.test_code.app.dto.PersonCreateResponse;
import io.philo.test_code.app.dto.PersonListResponse;
import io.philo.test_code.app.dto.PersonListResponses;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpStatus.CREATED;

public class PersonAcceptanceTest extends AbstractAcceptanceTest {

    @Autowired
    PersonRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @DisplayName("회원 생성 검증")
    @Test
    void assert_add() {

        // given & when
        var extractableResponse = post("/person", new PersonCreateRequest("philo"));

        // then
        int statusCode = extractableResponse.statusCode();
        long createdId = extractableResponse.as(PersonCreateResponse.class).id();

        assertAll(
                () -> assertThat(statusCode).isEqualTo(CREATED.value()),
                () -> assertThat(createdId).isPositive()
        );
    }

    @DisplayName("회원 조회 검증")
    @Test
    void assert_list() {

        // given & when
        post("/person", new PersonCreateRequest("philo-A"));
        post("/person", new PersonCreateRequest("philo-B"));
        post("/person", new PersonCreateRequest("philo-C"));

        // when
        List<PersonListResponse> peopleResponse = get("/person").as(PersonListResponses.class).people();


        // then
        assertAll(
                () -> assertThat(peopleResponse).hasSize(3),
                () -> assertThat(peopleResponse).extracting("personName").containsExactlyInAnyOrder("philo-A", "philo-B", "philo-C")
        );
    }
}
