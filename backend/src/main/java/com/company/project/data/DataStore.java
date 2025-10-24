package com.company.project.data;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Service
public class DataStore {
  private final Map<DOMAIN, List<FeedItem>> feedItems = Map.of(
      DOMAIN.LAW, new ArrayList<>(10)
  );
  private final Map<DOMAIN, List<ExtractPayload>> urls = Map.of(
    DOMAIN.LAW, List.of(
        new ExtractPayload("isap.sejm.gov.pl", "TODO")
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