package dev.coma.self.spring.dto;

import dev.coma.self.spring.entity.Member;

public class MemberDto {
  private String email;
  private String password;

  public MemberDto(String email, String password) {
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return "MemberDto{" +
            "email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

  public Member toEntity() {
    return new Member(null, email, password);
  }
}
