package com.company.project.service;

import com.company.project.data.DOMAIN;
import com.company.project.data.DataStore;
import com.company.project.data.FeedItem;
import com.company.project.data.Headline;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class DataInitializerService {

  private DataStore store;
  private ProcessingService processingService;
  private AiTrashService aiTrashService;

  @PostConstruct
  void initialize() {
    Map<DOMAIN, List<FeedItem>> headlines = store.getFeedItems();
    for(DOMAIN value : DOMAIN.values()) {
      headlines.put(value, store.getUrls().get(value).stream()
          .map(ePayload -> processingService.processheadline(ePayload))
          .map(headline -> new FeedItem(
              headline,
              aiTrashService.ruin(store.prepare_prompt(
                  store.getPrompts().get(value), headline))
          )).toList());
    }
  }
}