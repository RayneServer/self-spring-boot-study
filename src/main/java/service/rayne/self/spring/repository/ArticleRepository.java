package service.rayne.self.spring.repository;

import service.rayne.self.spring.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
  @Override
  ArrayList<Article> findAll();
}
