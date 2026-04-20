package service.rayne.self.spring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.rayne.self.spring.dto.CoffeeDto;
import service.rayne.self.spring.entity.Coffee;
import service.rayne.self.spring.repository.CoffeeRepository;
import service.rayne.self.spring.service.CoffeeService;

import java.util.List;
import java.util.Objects;

@RestController
public class CoffeeApiController {
  @Autowired
  private CoffeeService coffeeService;

  @GetMapping("/api/coffee")
  public List<Coffee> getCoffee() {
    return coffeeService.selectCoffeeAll();
  }

  @GetMapping("/api/coffee/{id}")
  public ResponseEntity<Coffee> getCoffeeId(@PathVariable Long id) {
    Coffee coffee = coffeeService.selectCoffeeById(id);

    if (Objects.isNull(coffee)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(coffee);
  }

  @PostMapping("/api/coffee")
  public ResponseEntity<Coffee> postCoffee(@RequestBody CoffeeDto dto) {
    Coffee coffee = coffeeService.insertCoffee(dto);

    if (Objects.isNull(coffee)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(coffee);
  }

  @PatchMapping("/api/coffee/{id}")
  public ResponseEntity<Coffee> patchCoffeeId(@PathVariable Long id, @RequestBody CoffeeDto dto) {
    Coffee coffee = coffeeService.updateCoffee(id, dto);

    if (Objects.isNull(coffee)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(coffee);
  }

  @DeleteMapping("/api/coffee/{id}")
  public ResponseEntity<Coffee> deleteCoffeeId(@PathVariable Long id) {
    Coffee coffee = coffeeService.deleteCoffee(id);

    if (Objects.isNull(coffee)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(coffee);
  }

  @PostMapping("/api/coffees")
  public ResponseEntity<List<Coffee>> postTransaction(@RequestBody List<CoffeeDto> dtos) {
    List<Coffee> coffeeList = coffeeService.insertCoffeeList(dtos);

    if (Objects.isNull(coffeeList)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(coffeeList);
  }
}
