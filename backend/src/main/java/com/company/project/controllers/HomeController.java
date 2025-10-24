package com.company.project.controllers;

import com.company.project.data.DOMAIN;
import com.company.project.data.DataStore;
import com.company.project.data.ExtractPayload;
import com.company.project.data.FeedItem;
import com.company.project.data.Headline;
import com.company.project.services.ErrorProcessor;
import com.company.project.services.ProcessingService;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
  private final ErrorProcessor err = new ErrorProcessor();
  private ProcessingService processingService;
  private DataStore store;

  @GetMapping("/")
  public String showHome(Model model) {
    List<Headline> headlines = processingService.processHeadlines(
        new ExtractPayload("https://en.wikipedia.org/", "#mp-itn b a"));
    if (headlines.isEmpty()) {
      err.displayError(model, "Wikipedia");
      return "error";
    }
    model.addAttribute("headlines", headlines);
    return "index";
  }

  @GetMapping("/test-gov-sites")
  public String showGovSites(Model model) {
    try {
      Document doc = Jsoup.connect("https://www.gov.pl/").get();
      System.out.println(doc.title());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "index";
  }

  @GetMapping("/ai")
  public String showAi(Model model) {
    List<FeedItem> feedItems = store.getFeedItems().get(DOMAIN.LAW);
    model.addAttribute("feedItems", feedItems);
    return "aiindex";
  }

  @GetMapping("/hello")
  public String showMarketRelated(Model model) {
    List<Headline> headlines = processingService.processHeadlines(
        new ExtractPayload("https://www.gov.pl/web/premier/wydarzenia", "div.title a"));
    if (headlines.isEmpty()) {
      err.displayError(model, "Gov.pl");
      return "error";
    }
    model.addAttribute("headlines", headlines);
    return "index";
  }
}