package com.company.project.services;

import com.company.project.data.DOMAIN;
import com.company.project.data.DataStore;
import com.company.project.data.FeedItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DataInitializerService {

  private DataStore store;
  private SerializerService serializer;
  private ProcessingService processingService;
  private AiService aiTrashService;

  @PostConstruct
  void initialize() {
    if (serializer.deserializeDataOnStartup() == 1) {
      Map<DOMAIN, List<FeedItem>> headlines = store.getFeedItems();
      for (DOMAIN domain : DOMAIN.values()) {
        headlines.put(domain, store.getUrls().get(domain).stream()
            .map(ePayload -> processingService.processHeadlines(ePayload,
                store.getSite_extraction_prompt().formatted(ePayload.url(), domain)))
            .flatMap(Collection::stream)
            .map(headline -> new FeedItem(
                headline,
                aiTrashService.ruin(store.prepare_prompt(
                    store.getPrompts().get(domain), headline))
            )).toList());
      }
    }
  }
}