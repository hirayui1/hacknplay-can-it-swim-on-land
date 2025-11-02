package com.company.project.services;

import com.company.project.data.ExtractPayload;
import com.company.project.data.Headline;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProcessingService {

  private AiService aiService;
  private final ObjectMapper objectMapper;

  public List<Headline> processHeadlines(ExtractPayload payload, String prompt) {
    log.info("PROCESSING payload: {}", payload.url());
    try {
      List<Headline> headlines = new ArrayList<>();
      Document doc = Jsoup.connect(payload.url()).get();
      log.info("EXTRACTED doc: {}", doc.title());
      String jsonLst = aiService.ruin(prompt.concat(doc.toString()));
      jsonLst = jsonLst.replace("```json", "");
      jsonLst = jsonLst.replace("```", "");
      jsonLst = jsonLst.replace("\n", "").replace("\r", "");
      Thread.sleep(1000);
      return objectMapper.readValue(jsonLst, new TypeReference<List<Headline>>() {});
//      for (Element headline : newsHeadlines) {
//        headlines.add(new Headline(headline.attr("title"),
//            headline.absUrl("href"), headline.attr("content")));
//      }
//      return headlines;
    } catch (Exception e) {
      e.printStackTrace();
      return List.of(new Headline(payload.url(), "Url read error", "Please message the support team", 10));
    }
  }
}