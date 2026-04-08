package service.rayne.self.spring.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.rayne.self.spring.dto.ArticleDto;
import service.rayne.self.spring.entity.Article;
import service.rayne.self.spring.service.ArticleService;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class ArticleApiController {
  @Autowired
  private ArticleService articleService;

  //GET
  @GetMapping("/api/articles")
  public List<Article> getApiArticles() {
    return articleService.selectArticleAll();
  }

  @GetMapping("/api/articles/{id}")
  public Article getApiArticlesId(@PathVariable Long id) {
    return articleService.selectArticleById(id);
  }

  //POST
  @PostMapping("/api/articles")
  public ResponseEntity<Article> postArticles(@RequestBody ArticleDto dto) {
    Article article = articleService.insertArticle(dto);

    return (Objects.isNull(article)) ?
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
      ResponseEntity.status(HttpStatus.OK).body(article);
  }

  //PATCH
  @PatchMapping("/api/articles/{id}")
  public ResponseEntity<Article> patchArticlesId(@PathVariable Long id, @RequestBody ArticleDto dto) {
    Article updatedArticle = articleService.updateArticle(id, dto);

    return (Objects.isNull(updatedArticle)) ?
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
      ResponseEntity.status(HttpStatus.OK).body(updatedArticle);
  }

  //DELETE
  @DeleteMapping("/api/articles/{id}")
  public ResponseEntity<Article> deleteArticlesId(@PathVariable Long id) {
    Article deletedArticle = articleService.deleteArticle(id);

    return (Objects.isNull(deletedArticle)) ?
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
      ResponseEntity.status(HttpStatus.OK).body(deletedArticle);
  }
}
