package service.rayne.self.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}