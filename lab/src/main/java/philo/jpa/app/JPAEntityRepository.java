package philo.jpa.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAEntityRepository extends JpaRepository<JPAEntity, Long> {

}
