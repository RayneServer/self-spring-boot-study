package dev.coma.self.spring.controller;

import dev.coma.self.spring.dto.ArticleDto;
import dev.coma.self.spring.entity.Article;
import dev.coma.self.spring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
  @Autowired
  private ArticleRepository articleRepository;

  @GetMapping("/articles/new")
  public String getArticlesNew() {
    return "articles/new";
  }

  @PostMapping("/articles/create")
  public String postArticlesCreate(ArticleDto dto) {
    // System.out.println(dto.toString());
    log.info(dto.toString());

    Article article = dto.toEntity();
    // System.out.println(article);
    log.info(article.toString());

    Article saved = articleRepository.save(article);
    // System.out.println(saved);
    log.info(saved.toString());

    return "";
  }

  @GetMapping("/articles")
  public String getArticles(Model model) {
    List<Article> articleList = articleRepository.findAll();
    model.addAttribute("articleList", articleList);

    return "articles/index";
  }

  @GetMapping("/articles/{id}")
  public String getArticlesId(@PathVariable Long id, Model model) {
    log.info("id = " + id);

    Article articleEntity = articleRepository.findById(id).orElse(null);
    model.addAttribute("article", articleEntity);

    return "articles/show";
  }
}
