package service.rayne.self.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import service.rayne.self.spring.entity.Pizza;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PizzaDto {
  private Long id;
  private String name;
  private Long price;

  public Pizza toEntity() {
    return new Pizza(id, name, price);
  }

  public static PizzaDto toDto(Pizza pizza) {
    return new PizzaDto(pizza.getId(), pizza.getName(), pizza.getPrice());
  }
}
