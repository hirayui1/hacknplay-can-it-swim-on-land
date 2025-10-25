package com.company.project.services;

import com.company.project.thymeleafWrapper.Headline;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProcessingService {

    public static List<Headline> processHeadlines(String url, String query) {
        try {
            List<Headline> headlines = new ArrayList<>();
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 Gecko/20100101 Firefox/21.0")
                    .timeout(0)
                    .get();
            System.out.println(doc.title());
            Elements newsHeadlines = doc.select(query); // this is quite useless as everybody name their css variables differently
            String title;
            String href;
            for (Element headline : newsHeadlines) {
                    headlines.add(new Headline(headline.attr("title"),
                        headline.absUrl("href")));
            }
            return headlines;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }
}