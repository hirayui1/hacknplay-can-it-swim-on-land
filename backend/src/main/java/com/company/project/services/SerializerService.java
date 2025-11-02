package com.company.project.services;

import com.company.project.data.DOMAIN;
import com.company.project.data.DataStore;
import com.company.project.data.FeedItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class SerializerService {

  private static final String SERIALIZED_FILE_NAME = "feedItems.ser";
  private DataStore store;

  public int deserializeDataOnStartup() {
    File serializedFile = new File(SERIALIZED_FILE_NAME);
    if (serializedFile.exists()) {
      try (FileInputStream fileIn = new FileInputStream(serializedFile);
           ObjectInputStream in = new ObjectInputStream(fileIn)) {
        Map<DOMAIN, List<FeedItem>> feedItems = (Map<DOMAIN, List<FeedItem>>) in.readObject();
        if (!Objects.isNull(feedItems))
          store.setFeedItems(feedItems);
        else
          return (1);
        log.info("Successfully deserialized feedItems from {}", SERIALIZED_FILE_NAME);
        return (0);
      } catch (IOException | ClassNotFoundException e) {
        log.error("Error deserializing feedItems", e);
      }
    } else
      log.info("Serialized feed data file not found, starting with an empty map.");
    return (1);
  }

  @PreDestroy
  public void serializeDataOnShutdown() {
    try (FileOutputStream fileOut = new FileOutputStream(SERIALIZED_FILE_NAME);
         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(store.getFeedItems());
      log.info("Successfully serialized feedItems to {}", SERIALIZED_FILE_NAME);
    } catch (IOException e) {
      log.error("Error serializing feedItems", e);
    }
  }
}