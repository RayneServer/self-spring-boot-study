package dev.coma.self.spring.controller;

import dev.coma.self.spring.dto.MemberDto;
import dev.coma.self.spring.entity.Member;
import dev.coma.self.spring.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class MemberController {
  @Autowired
  private MemberRepository memberRepository;

  @GetMapping("/signup")
  public String getSignup() {
    return "members/new";
  }

  @PostMapping("/join")
  public String postJoin(MemberDto dto) {
    // System.out.println(dto);
    log.info(dto.toString());

    Member member = dto.toEntity();
    // System.out.println(member);
    log.info(member.toString());

    Member saved = memberRepository.save(member);
    // System.out.println(saved);
    log.info(saved.toString());

    return "";
  }

  @GetMapping("/members/{id}")
  public String getMembersId(@PathVariable Long id, Model model) {
    Optional<Member> member = memberRepository.findById(id);
    model.addAttribute("member", member.orElse(null));

    return "members/show";
  }

  @GetMapping("/members")
  public String getMembers(Model model) {
    Iterable<Member> result = memberRepository.findAll();
    if (result instanceof List<Member> memberList) model.addAttribute("memberList", memberList);

    return "members/index";
  }
}
