package service.rayne.self.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import service.rayne.self.spring.dto.CommentDto;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne @JoinColumn(name = "article_id")
  private Article article;
  @Column
  private String nickname;
  @Column
  private String body;

  public static Comment toEntity(Article article, CommentDto dto) {
    if (!Objects.isNull(dto.getId())) throw new IllegalArgumentException("댓글의 ID 값이 없어야 합니다.");
    if (!Objects.equals(dto.getArticleId(), article.getId())) throw new IllegalArgumentException("게시글의 ID가 잘못되었습니다.");

    return new Comment(null, article, dto.getNickname(), dto.getBody());
  }

  public void patch(CommentDto dto) {
    // 예외 처리
    if (!Objects.equals(this.id, dto.getId())) throw new IllegalArgumentException("ID가 잘못되었습니다.");

    // 객체 수정
    if (!Objects.isNull(dto.getNickname())) this.nickname = dto.getNickname();
    if (!Objects.isNull(dto.getBody())) this.body = dto.getBody();
  }
}
