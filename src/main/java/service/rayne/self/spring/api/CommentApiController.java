package service.rayne.self.spring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.rayne.self.spring.service.CommentService;

@RestController
public class CommentApiController {
  @Autowired
  private CommentService commentService;

  // 1. 댓글 조회

  // 2. 댓글 생성
  
  // 3. 댓글 수정

  // 4. 댓글 삭제
}
