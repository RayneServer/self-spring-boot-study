package service.rayne.self.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.rayne.self.spring.dto.CommentDto;
import service.rayne.self.spring.entity.Comment;
import service.rayne.self.spring.repository.ArticleRepository;
import service.rayne.self.spring.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

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
}
