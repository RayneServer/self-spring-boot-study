package service.rayne.self.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.rayne.self.spring.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
