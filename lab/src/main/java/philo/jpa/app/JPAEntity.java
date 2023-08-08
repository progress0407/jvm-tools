package philo.jpa.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JPAEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  protected JPAEntity() {
  }

  public JPAEntity(String name) {
    this.name = name;
  }

  public void changeName(String name) {
    this.name = name;
  }
}
