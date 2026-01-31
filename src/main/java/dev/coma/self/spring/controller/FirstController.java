package dev.coma.self.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
  @GetMapping("/hi")
  public String getHi(Model model) {
    model.addAttribute("username", "Kumorin");

    return "greetings";
  }

  @GetMapping("/bye")
  public String getBye(Model model) {
    model.addAttribute("nickname", "CoMa");

    return "goodbye";
  }
}
