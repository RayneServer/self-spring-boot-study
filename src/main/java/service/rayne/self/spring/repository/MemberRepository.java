package service.rayne.self.spring.repository;

import service.rayne.self.spring.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
