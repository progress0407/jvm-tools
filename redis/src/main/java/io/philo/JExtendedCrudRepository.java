package io.philo;

import org.springframework.data.repository.CrudRepository;

public interface JExtendedCrudRepository<T, ID> extends CrudRepository<T, ID> {

    default T findByIdOrNull(ID id) {

        return findById(id).orElse(null);
    }
}
