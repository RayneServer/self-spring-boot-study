package service.rayne.self.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import service.rayne.self.spring.dto.ArticleDto;
import service.rayne.self.spring.entity.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
  @Autowired
  private ArticleService articleService;

  @Test
  void selectArticleAll() {
    // 1. 예상 데이터
    Article a = new Article(1L, "아오쿠모 린", "사랑해");
    Article b = new Article(2L, "쿠모린", "귀여워");
    Article c = new Article(3L, "뇨봇", "뇨");
    List<Article> expectList = new ArrayList<>(Arrays.asList(a, b, c));

    // 2. 실제 데이터
    List<Article> articleList = articleService.selectArticleAll();

    // 3. 비교 및 검증
    assertEquals(expectList.toString(), articleList.toString());
  }

  @Test
  void selectArticleById_Success_IdExist() {
    Article expect = new Article(1L, "아오쿠모 린", "사랑해");

    Long id = 1L;
    Article article = articleService.selectArticleById(id);

    assertEquals(expect.toString(), article.toString());
  }

  @Test
  void selectArticleById_Fail() {
    Article expect = null;

    Long id = -1L;
    Article article = articleService.selectArticleById(id);

    assertEquals(expect, article);
  }

  @Test
  @Transactional
  void insertArticle_Success_DtoWithTitleAndContent() {
    Article expectedResult = new Article(4L, "피뇨키오", "크리스마스");

    ArticleDto dto = new ArticleDto(null, "피뇨키오", "크리스마스");
    Article result = articleService.insertArticle(dto);

    assertEquals(expectedResult.toString(), result.toString());
  }

  @Test
  @Transactional
  void insertArticle_Fail_DtoWithId() {
    Article expectedResult = null;

    ArticleDto dto = new ArticleDto(4L, "피뇨키오", "크리스마스");
    Article result = articleService.insertArticle(dto);

    assertEquals(expectedResult, result);
  }

  @Test
  @Transactional
  void updateArticle_Success_DtoWithIdTitleAndContent() {
    Article expectedResult = new Article(1L, "쿠모린", "뇨봇됨");

    Long id = 1L;
    ArticleDto dto = new ArticleDto(1L, "쿠모린", "뇨봇됨");
    Article result = articleService.updateArticle(id, dto);

    assertEquals(expectedResult.toString(), result.toString());
  }

  @Test
  @Transactional
  void updateArticle_Success_DtoWithIdAndTitle() {
    Article expectedResult = new Article(1L, "쿠모린", "사랑해");

    Long id = 1L;
    ArticleDto dto = new ArticleDto(1L, "쿠모린", null);
    Article result = articleService.updateArticle(id, dto);

    assertEquals(expectedResult.toString(), result.toString());
  }

  @Test
  @Transactional
  void updateArticle_Fail_IdNotExist() {
    Article expectedResult = null;

    Long id = -1L;
    ArticleDto dto = new ArticleDto(-1L, "쿠모린", "뇨봇됨");
    Article result = articleService.updateArticle(id, dto);

    assertEquals(expectedResult, result);
  }

  @Test
  @Transactional
  void deleteArticle_Success() {
    Article expectedResult = new Article(1L, "아오쿠모 린", "사랑해");

    Long id = 1L;
    Article result = articleService.deleteArticle(id);

    assertEquals(expectedResult.toString(), result.toString());
  }

  @Test
  @Transactional
  void deleteArticle_Fail() {
    Article expectedResult = null;

    Long id = -1L;
    Article result = articleService.deleteArticle(id);

    assertEquals(expectedResult, result);
  }
}