package service.rayne.self.spring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.rayne.self.spring.dto.CommentDto;
import service.rayne.self.spring.service.CommentService;

import java.util.List;

@RestController
public class CommentApiController {
  @Autowired
  private CommentService commentService;

  // 1. 댓글 조회
  @GetMapping("/api/articles/{articleId}/comments")
  private ResponseEntity<List<CommentDto>> getComments(@PathVariable Long articleId) {
    // 서비스 호출
    List<CommentDto> commentDtoList = commentService.selectCommentAll(articleId);

    // 결과 응답
    return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
  }

  // 2. 댓글 생성

  // 3. 댓글 수정

  // 4. 댓글 삭제
}
