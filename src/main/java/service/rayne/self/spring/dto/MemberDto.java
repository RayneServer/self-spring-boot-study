package service.rayne.self.spring.dto;

import service.rayne.self.spring.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberDto {
  private String email;
  private String password;

  public Member toEntity() {
    return new Member(null, email, password);
  }
}
