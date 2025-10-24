package com.company.project.controllers;

import com.company.project.services.ErrorProcessor;
import com.company.project.services.ProcessingService;
import com.company.project.thymeleafWrapper.Headline;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {
    ErrorProcessor err = new ErrorProcessor();

    @GetMapping("/")
    public String showHome(Model model) {
        List<Headline> headlines = ProcessingService.processHeadlines("https://en.wikipedia.org/", "#mp-itn b a");
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

    @GetMapping("/hello")
    public String showMarketRelated(Model model) {
        List<Headline> headlines = ProcessingService.processHeadlines("https://www.gov.pl/web/premier/wydarzenia", "div.title a");
        if (headlines.isEmpty()) {
            err.displayError(model, "Gov.pl");
            return "error";
        }
        model.addAttribute("headlines", headlines);
        return "index";
    }
}