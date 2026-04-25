package service.rayne.self.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.rayne.self.spring.dto.CommentDto;
import service.rayne.self.spring.entity.Article;
import service.rayne.self.spring.entity.Comment;
import service.rayne.self.spring.repository.ArticleRepository;
import service.rayne.self.spring.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private ArticleRepository articleRepository;

  public List<CommentDto> selectCommentAll(Long articleId) {

    //    List<Comment> commentList = commentRepository.findByArticleId(articleId);
    //
    //    List<CommentDto> commentDtoList = new ArrayList<>();
    //    for (Comment comment : commentList) {
    //      CommentDto dto = CommentDto.toDto(comment);
    //      commentDtoList.add(dto);
    //    }

    // 스트림 문법으로 코드 리팩터링

    // 결과 반환
    return commentRepository.findByArticleId(articleId).stream().map(CommentDto::toDto).toList();
  }

  @Transactional
  public CommentDto insertComment(Long articleId, CommentDto dto) {
    // 1. 게시글 조회 (+ 예외 처리)
    Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!"));

    // 2. 댓글 Entity 생성
    Comment comment = Comment.toEntity(article, dto);

    // 3. 댓글 Entity를 DB에 저장
    Comment result = commentRepository.save(comment);

    // 4. DTO로 변환 후 반환
    return CommentDto.toDto(result);
  }

  @Transactional
  public CommentDto updateComment(Long id, CommentDto dto) {
    // 1. 댓글 조회 (+ 예외 처리)
    Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("대상 댓글이 없습니다."));

    // 2. 댓글 수정
    comment.patch(dto);

    // 3. DB 갱신
    Comment updatedComment = commentRepository.save(comment);

    // 4. DTO로 변환 후 반환
    return CommentDto.toDto(updatedComment);
  }

  public CommentDto deleteComment(Long id) {
    // 1. 댓글 조회 (+ 예외 처리)
    Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("대상이 없습니다."));

    // 2. 댓글 삭제
    commentRepository.delete(comment);

    // 3. DTO로 변환 후 반환
    return CommentDto.toDto(comment);
  }
}
