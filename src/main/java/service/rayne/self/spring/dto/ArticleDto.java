package service.rayne.self.spring.dto;

import service.rayne.self.spring.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleDto {
  private String title;
  private String content;

  public Article toEntity() {
    return new Article(null, title, content);
  }
}
