package service.rayne.self.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String title;
  @Column
  private String content;

  public void patch(Article article) {
    if (!Objects.isNull(article.title)) this.title = article.title;
    if (!Objects.isNull(article.content)) this.content = article.content;
  }
}
