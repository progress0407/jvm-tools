package philo.jpa.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * JPA 의 변경 이벤트를 추적하기 위해 만든 간이 앱
 * ActionControl
 * Flush 등
 */
@Controller
@RequestMapping("/jpa-test")
public class JPAController {

  private final JPAService service;

  public JPAController(JPAService service) {
    this.service = service;
  }

  @PostMapping("/save")
  public void save() {
    service.save();
  }

  @PostMapping("/update")
  public void dirtyCheckAndUpdate() {
    service.dirtyCheckAndUpdate();
  }
}
