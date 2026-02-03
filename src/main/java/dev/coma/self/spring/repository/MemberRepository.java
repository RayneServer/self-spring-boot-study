package dev.coma.self.spring.repository;

import dev.coma.self.spring.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
