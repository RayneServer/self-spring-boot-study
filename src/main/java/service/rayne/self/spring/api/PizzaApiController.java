package service.rayne.self.spring.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.rayne.self.spring.dto.PizzaDto;
import service.rayne.self.spring.service.PizzaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PizzaApiController {
  private final PizzaService pizzaService;

  // 목록 조회
  @GetMapping("/api/pizza")
  public ResponseEntity<List<PizzaDto>> getPizzaAll() {
    return ResponseEntity.ok(pizzaService.selectPizzaAll());
  }

  // 단일 조회
  @GetMapping("/api/pizza/{id}")
  public ResponseEntity<PizzaDto> getPizza(@PathVariable Long id) {
    return pizzaService.selectPizza(id).map(ResponseEntity::ok).orElseThrow(() -> new IllegalArgumentException("대상이 없습니다!"));
  }

  // 피자 생성
  @PostMapping("/api/pizza")
  public ResponseEntity<PizzaDto> postPizza(@RequestBody PizzaDto dto) {
    return ResponseEntity.ok(pizzaService.insertPizza(dto));
  }

  // 피자 수정
  @PatchMapping("/api/pizza/{id}")
  public ResponseEntity<PizzaDto> patchPizza(@PathVariable Long id, @RequestBody PizzaDto dto) {
    return ResponseEntity.ok(pizzaService.updatePizza(id, dto));
  }

  // 피자 삭제
  @DeleteMapping("/api/pizza/{id}")
  public ResponseEntity<PizzaDto> deletePizza(@PathVariable Long id) {
    return ResponseEntity.ok(pizzaService.deletePizza(id));
  }
}
