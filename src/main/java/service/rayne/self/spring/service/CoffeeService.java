package service.rayne.self.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import service.rayne.self.spring.dto.CoffeeDto;
import service.rayne.self.spring.entity.Coffee;
import service.rayne.self.spring.repository.CoffeeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class CoffeeService {
  @Autowired
  private CoffeeRepository coffeeRepository;

  public List<Coffee> selectCoffeeAll() {
    return coffeeRepository.findAll();
  }

  public Coffee selectCoffeeById(Long id) {
    return coffeeRepository.findById(id).orElse(null);
  }

  public Coffee insertCoffee(CoffeeDto dto) {
    Coffee coffee = dto.toEntity();
    if (!Objects.isNull(coffee.getId())) return null;

    return coffeeRepository.save(coffee);
  }

  public Coffee updateCoffee(Long id, CoffeeDto dto) {
    Coffee coffee = dto.toEntity();
    Coffee targetCoffee = coffeeRepository.findById(id).orElse(null);

    if (Objects.isNull(targetCoffee) || !id.equals(coffee.getId())) return null;

    return coffeeRepository.save(coffee);
  }

  public Coffee deleteCoffee(Long id) {
    Coffee targetCoffee = coffeeRepository.findById(id).orElse(null);

    if (Objects.isNull(targetCoffee)) return null;

    coffeeRepository.delete(targetCoffee);
    return targetCoffee;
  }

  public List<Coffee> insertCoffeeList(List<CoffeeDto> dtos) {
    List<Coffee> coffeeList = dtos.stream().map(CoffeeDto::toEntity).toList();
    coffeeRepository.saveAll(coffeeList);

    return coffeeList;
  }
}
