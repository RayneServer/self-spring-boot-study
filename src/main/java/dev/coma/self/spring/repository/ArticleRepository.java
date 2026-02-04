package dev.coma.self.spring.repository;

import dev.coma.self.spring.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
  @Override
  ArrayList<Article> findAll();
}
