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
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
//  private final ErrorProcessor err = new ErrorProcessor();
//  private ProcessingService processingService;
  private DataStore store;

//  @GetMapping("/test-gov-sites")
//  public String showGovSites(Model model) {
//    try {
//        Document doc = Jsoup.connect("https://www.gov.pl/").get();
//        System.out.println(doc.title());
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//        return "index";
//    }

    @GetMapping("/law")
    public String showLaw(Model model) {
        List<FeedItem> feedItems = store.getFeedItems().get(DOMAIN.LAW);
        model.addAttribute("feedItems", feedItems);
        return "ai_list";
    }

    @GetMapping("/company")
    public String showCompany(Model model) {
        List<FeedItem> feedItems = store.getFeedItems().get(DOMAIN.COMPANY_STATUS);
        model.addAttribute("feedItems", feedItems);
        return "ai_list";
    }

    @GetMapping("/marketing")
    public String showMarketing(Model model) {
        List<FeedItem> feedItems = store.getFeedItems().get(DOMAIN.MARKETING);
        model.addAttribute("feedItems", feedItems);
        return "ai_list";
    }

    @GetMapping("/media")
    public String showMedia(Model model) {
        List<FeedItem> feedItems = store.getFeedItems().get(DOMAIN.MEDIA);
        model.addAttribute("feedItems", feedItems);
        return "ai_list";
    }
}