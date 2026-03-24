package service.rayne.self.spring.dto;

import service.rayne.self.spring.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleDto {
  private Long id;
  private String title;
  private String content;

  public Article toEntity() {
    return new Article(id, title, content);
  }
}
