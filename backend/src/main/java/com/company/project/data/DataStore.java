package com.company.project.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Service
public class DataStore {
  private Map<DOMAIN, List<FeedItem>> feedItems = new HashMap<>();
  private final Map<DOMAIN, List<ExtractPayload>> urls = Map.of(
      DOMAIN.LAW, List.of(
          new ExtractPayload("https://en.wikipedia.org/", "#mp-itn b a"),
          new ExtractPayload("https://www.gov.pl/web/premier/wydarzenia", "div.title a")
      )
  );
  private final Map<DOMAIN, String> prompts = Map.of(
      DOMAIN.LAW, """
          Summarize the following parsed content, be extremely serious, informative, formal and cold
          TITLE: %s
          CONTENT: %s
          """
  );

  public String prepare_prompt(String s, Headline headline) {
    return s.formatted(headline.title(), headline.content());
  }
}