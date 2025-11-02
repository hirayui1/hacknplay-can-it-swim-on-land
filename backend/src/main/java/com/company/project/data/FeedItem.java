package com.company.project.data;

import java.io.Serial;
import java.io.Serializable;

public record FeedItem(Headline headline, String ruin) implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
}