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
          new ExtractPayload(
              "https://bip.uke.gov.pl",
              "div.news-list-item",
              "a.news-list-item-link",
              "h3.news-list-item-title",
              "p.news-list-item-lead"
          )
      ),
      DOMAIN.COMPANY_STATUS, List.of(
          new ExtractPayload(
              "https://telko.in",
              "div.article--list-item",
              "a.article__link",
              "h2.article__title",
              "p.article__lead"
          )
      ),
      DOMAIN.MARKETING, List.of(
          new ExtractPayload(
              "https://wirtualnemedia.pl/w/telekomunikacja",
              "article.news-item",
              "a.news-link",
              "h3.news-title",
              "p.news-lead"
          )
      ),
      DOMAIN.MEDIA, List.of(
          new ExtractPayload(
              "https://telepolis.pl",
              "article.widget--article",
              "a.widget__link",
              "h2.widget__title",
              "p.widget__lead"
          )
      ));

  private final Map<DOMAIN, String> prompts = Map.of(
      DOMAIN.LAW, """
          Summarize the following parsed content, be extremely serious, informative, formal and cold
          TITLE: %s
          CONTENT: %s
          """,
      DOMAIN.COMPANY_STATUS, """
          Summarize the following parsed content, be extremely serious, informative, formal and cold
          TITLE: %s
          CONTENT: %s
          """,
      DOMAIN.MEDIA, """
          Summarize the following parsed content, be extremely serious, informative, formal and cold
          TITLE: %s
          CONTENT: %s
          """,
      DOMAIN.MARKETING, """
          Summarize the following parsed content, be extremely serious, informative, formal and cold
          TITLE: %s
          CONTENT: %s
          """
  );

  public String prepare_prompt(String s, Headline headline) {
    return s.formatted(headline.title(), headline.content());
  }
}