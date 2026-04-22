package service.rayne.self.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import service.rayne.self.spring.entity.Article;
import service.rayne.self.spring.entity.Comment;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
  @Autowired
  private CommentRepository commentRepository;

  @Test
  @DisplayName("특정 게시글의 모든 댓글 조회")
  void findByArticleId() {
    // Case 1: 4번 게시글의 모든 댓글 조회
    {
      // 1. 입력 데이터 준비
      Long articleId = 4L;

      // 2. 실제 데이터
      List<Comment> result = commentRepository.findByArticleId(articleId);

      // 3. 예상 데이터
      Article article = new Article(4L, "린의 인생 치킨은?", "댓글 고");
      Comment a = new Comment(1L, article, "유니", "부르다끄");
      Comment b = new Comment(2L, article, "후야", "굽네 바사삭");
      Comment c = new Comment(3L, article, "히나", "뿌링클");
      List<Comment> expect = Arrays.asList(a, b, c);

      // 4. 비교 및 검증
      assertEquals(expect.toString(), result.toString(), "4번 글의 모든 댓글 출력!");
    }

    // Case 2: 1번 게시글의 모든 댓글 조회
    {
      // 1. 입력 데이터 준비
      Long articleId = 1L;

      // 2. 실제 데이터
      List<Comment> result = commentRepository.findByArticleId(articleId);

      // 3. 예상 데이터
      Article article = new Article(1L, "아오쿠모 린", "사랑해");
      List<Comment> expect = Arrays.asList();

      // 4. 비교 및 검증
      assertEquals(expect.toString(), result.toString(), "1번 글은 댓글이 없음");
    }

    // Case 3: 9번 게시글의 모든 댓글 조회
    {
      // 1. 입력 데이터 준비
      Long articleId = 9L;

      // 2. 실제 데이터
      List<Comment> result = commentRepository.findByArticleId(articleId);

      // 3. 예상 데이터
      List<Comment> expect = Arrays.asList();

      // 4. 비교 및 검증
      assertEquals(expect.toString(), result.toString(), "9번 글은 없음");
    }

    // Case 4: 999번 게시글의 모든 댓글 조회
    {
      // 1. 입력 데이터 준비
      Long articleId = 999L;

      // 2. 실제 데이터
      List<Comment> result = commentRepository.findByArticleId(articleId);

      // 3. 예상 데이터
      List<Comment> expect = Arrays.asList();

      // 4. 비교 및 검증
      assertEquals(expect.toString(), result.toString(), "999번 글은 없음");
    }

    // Case 5: -1번 게시글의 모든 댓글 조회
    {
      // 1. 입력 데이터 준비
      Long articleId = -1L;

      // 2. 실제 데이터
      List<Comment> result = commentRepository.findByArticleId(articleId);

      // 3. 예상 데이터
      List<Comment> expect = Arrays.asList();

      // 4. 비교 및 검증
      assertEquals(expect.toString(), result.toString(), "-1번 글은 없음");
    }
  }

  @Test
  @DisplayName("특정 닉네임의 모든 댓글 조회")
  void findByNickname() {
    // Case 1: "유니"의 모든 댓글 조회
    {
      String nickname = "유니";
      List<Comment> result = commentRepository.findByNickname(nickname);

      Comment a = new Comment(1L, new Article(4L, "린의 인생 치킨은?", "댓글 고"), nickname, "부르다끄");
      Comment b = new Comment(4L, new Article(5L, "린의 인생 야채는?", "댓글 고고"), nickname, "습박 야채를 왜 먹여");
      Comment c = new Comment(7L, new Article(6L, "린의 취미는?", "댓글 고고고"), nickname, "이리");
      List<Comment> expect = Arrays.asList(a, b, c);

      assertEquals(expect.toString(), result.toString(), "유니의 모든 댓글 출력!");
    }

    // Case 2: "리제"의 모든 댓글 조회
    {
      String nickname = "리제";
      List<Comment> result = commentRepository.findByNickname(nickname);

      Comment b = new Comment(5L, new Article(5L, "린의 인생 야채는?", "댓글 고고"), nickname, "토마토");
      List<Comment> expect = Arrays.asList(b);

      assertEquals(expect.toString(), result.toString(), "리제의 모든 댓글 출력!");
    }

    // Case 3: null의 모든 댓글 조회
    {
      String nickname = null;
      List<Comment> result = commentRepository.findByNickname(nickname);

      List<Comment> expect = Arrays.asList();

      assertEquals(expect.toString(), result.toString(), "null의 모든 댓글 출력!");
    }

    // Case 4: ""의 모든 댓글 조회
    {
      String nickname = "";
      List<Comment> result = commentRepository.findByNickname(nickname);

      List<Comment> expect = Arrays.asList();

      assertEquals(expect.toString(), result.toString(), "\"\"의 모든 댓글 출력!");
    }
  }
}