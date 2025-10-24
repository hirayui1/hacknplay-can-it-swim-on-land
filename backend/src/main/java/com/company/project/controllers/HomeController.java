package com.company.project.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HomeController {

    @GetMapping("/")
    public String showHome() {
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
            System.out.println(doc.title());
            Elements newsHeadlines = doc.select("#mp-itn b a");
            StringBuilder sb = new StringBuilder();
            for (Element headline : newsHeadlines) {
                sb.append("%s\n\t%s").append(" ")
                        .append(headline.attr("title")).append(" ").append(headline.absUrl("href"));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "something went wrong?";
    }
}