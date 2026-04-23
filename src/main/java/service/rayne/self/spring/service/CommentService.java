package service.rayne.self.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.rayne.self.spring.repository.ArticleRepository;
import service.rayne.self.spring.repository.CommentRepository;

@Service
public class CommentService {
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private ArticleRepository articleRepository;
}
