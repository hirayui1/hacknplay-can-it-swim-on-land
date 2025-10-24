package com.company.project.services;

import com.company.project.data.ExtractPayload;
import com.company.project.data.Headline;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProcessingService {

  public List<Headline> processHeadlines(ExtractPayload payload) {
    log.info("PROCESSING payload: {} {}", payload.url(), payload.cssSelector());
    try {
      List<Headline> headlines = new ArrayList<>();
      Document doc = Jsoup.connect(payload.url()).get();
      System.out.println(doc.title());
      Elements newsHeadlines = doc.select(payload.cssSelector());

      for (Element headline : newsHeadlines) {
        headlines.add(new Headline(headline.attr("title"),
            headline.absUrl("href"), headline.attr("content")));
      }
      return headlines;
    } catch (Exception e) {
      e.printStackTrace();
      return List.of(new Headline(payload.url(), "Url read error", "That is sad too"));
    }
  }
}