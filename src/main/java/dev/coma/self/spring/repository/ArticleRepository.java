package dev.coma.self.spring.repository;

import dev.coma.self.spring.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
