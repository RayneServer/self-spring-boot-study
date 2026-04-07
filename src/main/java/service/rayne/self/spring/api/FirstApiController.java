package service.rayne.self.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstApiController {

  @GetMapping("/api/hello")
  public String getHello() {
    return "Hello World!";
  }
}
