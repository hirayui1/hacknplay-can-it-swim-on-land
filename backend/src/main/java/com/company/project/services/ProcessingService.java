package com.company.project.services;

import com.company.project.data.DataStore;
import com.company.project.data.ExtractPayload;
import com.company.project.data.Headline;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Slf4j
public class ProcessingService {

  private AiTrashService aiTrashService;
  private DataStore store;

  public List<Headline> processHeadlines(ExtractPayload payload, String prompt) {
    log.info("PROCESSING payload: {}", payload.url());
    try {
      List<Headline> headlines = new ArrayList<>();
      Document doc = Jsoup.connect(payload.url()).get();
      log.info("EXTRACTED doc: {}", doc.title());
//      Elements newsHeadlines = doc.select(payload.cssSelector());
//      String jsonLst = aiTrashService.ruin(prompt.concat(doc.toString()));
//      for (Element headline : newsHeadlines) {
//        headlines.add(new Headline(headline.attr("title"),
//            headline.absUrl("href"), headline.attr("content")));
//      }
      return headlines;
    } catch (Exception e) {
      e.printStackTrace();
      return List.of(new Headline(payload.url(), "Url read error", "That is sad too", 10));
    }
  }
}