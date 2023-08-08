package philo.jpa.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JPAService {

  private final JPAEntityRepository repository;

  public JPAService(JPAEntityRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public void save() {
    JPAEntity entity = new JPAEntity("one");
    repository.save(entity);
  }

  @Transactional
  public void dirtyCheckAndUpdate() {
    JPAEntity entity = new JPAEntity("one");
    repository.save(entity);

    entity.changeName("two");
  }
}
