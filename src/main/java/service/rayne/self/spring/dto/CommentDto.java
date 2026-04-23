package service.rayne.self.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import service.rayne.self.spring.entity.Comment;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
  private Long id; // 댓글 ID
  private Long articleId; // 게시글 ID
  private String nickname; // 댓글 작성자
  private String body; // 댓글 내용

  public static CommentDto toDto(Comment comment) {
    return new CommentDto(comment.getId(), comment.getArticle().getId(), comment.getNickname(), comment.getBody());
  }
}
