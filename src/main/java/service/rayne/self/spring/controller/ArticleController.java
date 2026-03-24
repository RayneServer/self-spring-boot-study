package service.rayne.self.spring.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.rayne.self.spring.dto.ArticleDto;
import service.rayne.self.spring.entity.Article;
import service.rayne.self.spring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

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

    return "redirect:/articles/" + saved.getId();
  }

  @GetMapping("/articles")
  public String getArticles(Model model) {
    List<Article> articleList = articleRepository.findAll();
    model.addAttribute("articleList", articleList);

    return "articles/index";
  }

  @GetMapping("/articles/{id}/edit")
  public String getArticlesEdit(@PathVariable Long id, Model model) {
    Article articleEntity = articleRepository.findById(id).orElse(null);
    model.addAttribute("article", articleEntity);

    return "articles/edit";
  }

  @PostMapping("/articles/update")
  public String postArticlesUpdate(ArticleDto dto) {
    log.info(dto.toString());

    Article article = dto.toEntity();
    // System.out.println(article);
    log.info(article.toString());

    Article before = articleRepository.findById(article.getId()).orElse(null);
    if (!Objects.isNull(before)) articleRepository.save(article);

    return "redirect:/articles/" + article.getId();
  }

  @GetMapping("/articles/{id}/delete")
  public String getArticlesDelete(@PathVariable Long id, RedirectAttributes reAttr) {
    log.info("삭제 요청 받음!!!");

    // 삭제 대상 가져오기
    Article deleteTargetArticle = articleRepository.findById(id).orElse(null);
    log.info(Objects.isNull(deleteTargetArticle) ? "대상이 없습니다." : deleteTargetArticle.toString());

    // 대상 엔티티 삭제
    if (!Objects.isNull(deleteTargetArticle)) {
      articleRepository.delete(deleteTargetArticle);
      reAttr.addFlashAttribute("msg", "삭제됐습니다!");
    }

    // 결과 페이지로 리다이렉트
    return "redirect:/articles";
  }

  @GetMapping("/articles/{id}")
  public String getArticlesId(@PathVariable Long id, Model model) {
    log.info("id = " + id);

    Article articleEntity = articleRepository.findById(id).orElse(null);
    model.addAttribute("article", articleEntity);

    return "articles/show";
  }
}
