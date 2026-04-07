package service.rayne.self.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.rayne.self.spring.entity.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
