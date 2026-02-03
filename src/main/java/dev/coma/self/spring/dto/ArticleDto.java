package dev.coma.self.spring.dto;

import dev.coma.self.spring.entity.Article;
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
