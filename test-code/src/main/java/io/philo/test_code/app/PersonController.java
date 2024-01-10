package io.philo.test_code.app;

import io.philo.test_code.app.dto.PersonCreateRequest;
import io.philo.test_code.app.dto.PersonCreateResponse;
import io.philo.test_code.app.dto.PersonListResponse;
import io.philo.test_code.app.dto.PersonListResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonCreateResponse add(@RequestBody PersonCreateRequest request) {

        String name = request.name();
        Person person = new Person(name);
        repository.save(person);

        return new PersonCreateResponse(person.getId());
    }

    @GetMapping
    public PersonListResponses list() {

        List<Person> entities = repository.findAll();
        List<PersonListResponse> dtos = entities.stream()
                .map(PersonListResponse::new)
                .toList();

        return new PersonListResponses(dtos);
    }
}
