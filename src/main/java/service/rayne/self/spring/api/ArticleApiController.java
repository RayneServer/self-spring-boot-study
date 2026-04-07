package service.rayne.self.spring.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.rayne.self.spring.dto.ArticleDto;
import service.rayne.self.spring.entity.Article;
import service.rayne.self.spring.repository.ArticleRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class ArticleApiController {
  @Autowired
  private ArticleRepository articleRepository;

  //GET
  @GetMapping("/api/articles")
  public List<Article> getApiArticles() {
    return articleRepository.findAll();
  }

  @GetMapping("/api/articles/{id}")
  public Article getApiArticlesId(@PathVariable Long id) {
    return articleRepository.findById(id).orElse(null);
  }

  //POST
  @PostMapping("/api/articles")
  public Article postArticles(@RequestBody ArticleDto dto) {
    Article article = dto.toEntity();
    return articleRepository.save(article);
  }

  //PATCH
  @PatchMapping("/api/articles/{id}")
  public ResponseEntity<Article> patchArticlesId(@PathVariable Long id, @RequestBody ArticleDto dto) {
    // 1. Entity 변환
    Article article = dto.toEntity();
    log.info("id: {}, article: {}", id, article);

    // 2. 대상 조회
    Article targetArticle = articleRepository.findById(id).orElse(null);

    // 3. 잘못된 요청 처리
    if (Objects.isNull(targetArticle) || !id.equals(article.getId())) {
      log.info("잘못된 요청!! id: {}, article: {}", id, article.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 4. 정상 요청 처리
    targetArticle.patch(article);
    Article updatedArticle = articleRepository.save(targetArticle);
    return ResponseEntity.status(HttpStatus.OK).body(updatedArticle);
  }

  //DELETE
  @DeleteMapping("/api/articles/{id}")
  public ResponseEntity<Article> deleteArticlesId(@PathVariable Long id) {
    // 1. 대상 검색
    Article targetArticle = articleRepository.findById(id).orElse(null);

    // 2. 잘못된 요청 처리
    if (Objects.isNull(targetArticle)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 3. 정상 요청 처리
    articleRepository.delete(targetArticle);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
