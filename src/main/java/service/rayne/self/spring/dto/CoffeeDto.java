package service.rayne.self.spring.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import service.rayne.self.spring.entity.Coffee;

@AllArgsConstructor
@ToString
public class CoffeeDto {
  private Long id;
  private String name;
  private Long price;

  public Coffee toEntity() {
    return new Coffee(id, name, price);
  }
}
