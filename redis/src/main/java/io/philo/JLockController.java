package io.philo;

import io.philo.app.Lock;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test/lock-2")
@RestController
public class JLockController {

    private final JLockRepository lockRepo2;

    public JLockController(JLockRepository lockRepo2) {
        this.lockRepo2 = lockRepo2;
    }

    @RequestMapping("/get/{id}")
    public Object get(@PathVariable String id) {
        return lockRepo2.findByIdOrNull(id);
    }

    @RequestMapping("/set/{id}")
    public Object set(@PathVariable String id) {
        return lockRepo2.save(new Lock(id, 30));
    }
}
