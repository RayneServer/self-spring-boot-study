package dev.coma.self.spring.controller;

import dev.coma.self.spring.dto.ArticleDto;
import dev.coma.self.spring.entity.Article;
import dev.coma.self.spring.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
  @Autowired
  private ArticleRepository articleRepository;

  @GetMapping("/articles/new")
  public String getArticlesNew() {
    return "articles/new";
  }

  @PostMapping("/articles/create")
  public String postArticlesCreate(ArticleDto dto) {
    System.out.println(dto.toString());

    Article article = dto.toEntity();
    System.out.println(article);

    Article saved = articleRepository.save(article);
    System.out.println(saved);

    return "";
  }
}
