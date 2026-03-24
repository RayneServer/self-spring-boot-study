package service.rayne.self.spring.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.rayne.self.spring.dto.MemberDto;
import service.rayne.self.spring.entity.Member;
import service.rayne.self.spring.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;
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

    return "redirect:/members/" + saved.getId();
  }

  @GetMapping("/members/{id}")
  public String getMembersId(@PathVariable Long id, Model model) {
    Optional<Member> member = memberRepository.findById(id);
    model.addAttribute("member", member.orElse(null));

    return "members/show";
  }

  @GetMapping("/members/{id}/edit")
  public String getMembersEdit(@PathVariable Long id, Model model) {
    Member member = memberRepository.findById(id).orElse(null);
    model.addAttribute("member", member);

    return "members/edit";
  }

  @PostMapping("/members/update")
  public String postMembersUpdate(MemberDto dto) {
    Member member = dto.toEntity();

    Member before = memberRepository.findById(member.getId()).orElse(null);
    if (!Objects.isNull(before)) memberRepository.save(member);

    return "redirect:/members/" + member.getId();
  }

  @GetMapping("/members/{id}/delete")
  public String getMembersDelete(@PathVariable Long id, RedirectAttributes reAttr) {
    Member deleteTargetMember = memberRepository.findById(id).orElse(null);
    if (!Objects.isNull(deleteTargetMember)) {
      memberRepository.delete(deleteTargetMember);
      reAttr.addFlashAttribute("msg", "삭제했습니다!!");
    }

    return "redirect:/members";
  }

  @GetMapping("/members")
  public String getMembers(Model model) {
    Iterable<Member> result = memberRepository.findAll();
    if (result instanceof List<Member> memberList) model.addAttribute("memberList", memberList);

    return "members/index";
  }
}
