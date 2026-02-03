package dev.coma.self.spring.controller;

import dev.coma.self.spring.dto.MemberDto;
import dev.coma.self.spring.entity.Member;
import dev.coma.self.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
  @Autowired
  private MemberRepository memberRepository;

  @GetMapping("/signup")
  public String getSignup() {
    return "members/new";
  }

  @PostMapping("/join")
  public String postJoin(MemberDto dto) {
    System.out.println(dto);

    Member member = dto.toEntity();
    System.out.println(member);

    Member saved = memberRepository.save(member);
    System.out.println(saved);

    return "";
  }
}
