package service.rayne.self.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import service.rayne.self.spring.dto.ArticleDto;
import service.rayne.self.spring.entity.Article;
import service.rayne.self.spring.repository.ArticleRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ArticleService {
  @Autowired
  private ArticleRepository articleRepository;

  public List<Article> selectArticleAll() {
    return articleRepository.findAll();
  }

  public Article selectArticleById(Long id) {
    return articleRepository.findById(id).orElse(null);
  }

  public Article insertArticle(ArticleDto dto) {
    Article article = dto.toEntity();

    if (!Objects.isNull(article.getId())) return null;

    return articleRepository.save(article);
  }

  public Article updateArticle(Long id, ArticleDto dto) {
    // 1. Entity 변환
    Article article = dto.toEntity();
    log.info("id: {}, article: {}", id, article);

    // 2. 대상 조회
    Article targetArticle = articleRepository.findById(id).orElse(null);

    // 3. 잘못된 요청 처리
    if (Objects.isNull(targetArticle) || !id.equals(article.getId())) {
      log.info("잘못된 요청!! id: {}, article: {}", id, article.toString());
      return null;
    }

    // 4. 정상 요청 처리
    targetArticle.patch(article);
    return articleRepository.save(targetArticle);
  }

  public Article deleteArticle(Long id) {
    // 1. 대상 검색
    Article targetArticle = articleRepository.findById(id).orElse(null);

    // 2. 잘못된 요청 처리
    if (Objects.isNull(targetArticle)) {
      return null;
    }

    // 3. 정상 요청 처리
    articleRepository.delete(targetArticle);
    return targetArticle;
  }
}
