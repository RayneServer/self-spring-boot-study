package service.rayne.self.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.rayne.self.spring.dto.PizzaDto;
import service.rayne.self.spring.entity.Pizza;
import service.rayne.self.spring.repository.PizzaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PizzaService {
  private final PizzaRepository pizzaRepository;

  public List<PizzaDto> selectPizzaAll() {
    return pizzaRepository.findAll().stream().map(PizzaDto::toDto).toList();
  }

  public Optional<PizzaDto> selectPizza(Long id) {
    return pizzaRepository.findById(id).map(PizzaDto::toDto);
  }

  @Transactional
  public PizzaDto insertPizza(PizzaDto dto) {
    if (!Objects.isNull(dto.getId())) throw new IllegalArgumentException("ID값이 있으면 안돼요!");

    Pizza result = pizzaRepository.save(dto.toEntity());
    return PizzaDto.toDto(result);
  }

  @Transactional
  public PizzaDto updatePizza(Long id, PizzaDto dto) {
    Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("대상이 없습니다!"));

    pizza.patch(dto);
    Pizza updatedPizza = pizzaRepository.save(pizza);
    return PizzaDto.toDto(updatedPizza);
  }

  @Transactional
  public PizzaDto deletePizza(Long id) {
    Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("대상이 없습니다!"));

    pizzaRepository.delete(pizza);
    return PizzaDto.toDto(pizza);
  }
}
