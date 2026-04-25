package service.rayne.self.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import service.rayne.self.spring.dto.PizzaDto;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pizza {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String name;
  @Column
  private Long price;

  public void patch(PizzaDto dto) {
    if (!Objects.equals(this.id, dto.getId())) throw new IllegalArgumentException("ID값이 잘못되었습니다!");

    if (!Objects.isNull(dto.getName())) this.name = dto.getName();
    if (!Objects.isNull(dto.getPrice())) this.price = dto.getPrice();
  }
}
