package service.rayne.self.spring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.rayne.self.spring.dto.CoffeeDto;
import service.rayne.self.spring.entity.Coffee;
import service.rayne.self.spring.repository.CoffeeRepository;

import java.util.List;
import java.util.Objects;

@RestController
public class CoffeeApiController {
  @Autowired
  private CoffeeRepository coffeeRepository;

  @GetMapping("/api/coffee")
  public List<Coffee> getCoffee() {
    return coffeeRepository.findAll();
  }

  @GetMapping("/api/coffee/{id}")
  public ResponseEntity<Coffee> getCoffeeId(@PathVariable Long id) {
    Coffee coffee = coffeeRepository.findById(id).orElse(null);

    if (Objects.isNull(coffee)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(coffee);
  }

  @PostMapping("/api/coffee")
  public Coffee postCoffee(@RequestBody CoffeeDto dto) {
    Coffee coffee = dto.toEntity();

    return coffeeRepository.save(coffee);
  }

  @PatchMapping("/api/coffee/{id}")
  public ResponseEntity<Coffee> patchCoffeeId(@PathVariable Long id, @RequestBody CoffeeDto dto) {
    Coffee coffee = dto.toEntity();
    Coffee targetCoffee = coffeeRepository.findById(id).orElse(null);

    if (Objects.isNull(targetCoffee) || !id.equals(coffee.getId())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    Coffee updatedCoffee = coffeeRepository.save(coffee);
    return ResponseEntity.status(HttpStatus.OK).body(updatedCoffee);
  }

  @DeleteMapping("/api/coffee/{id}")
  public ResponseEntity<Coffee> deleteCoffeeId(@PathVariable Long id) {
    Coffee targetCoffee = coffeeRepository.findById(id).orElse(null);

    if (Objects.isNull(targetCoffee)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    coffeeRepository.delete(targetCoffee);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
