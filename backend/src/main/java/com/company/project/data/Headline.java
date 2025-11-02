package com.company.project.data;

import java.io.Serial;
import java.io.Serializable;

public record Headline(
    String url,
    String title,
    String content,
    Integer relevance
) implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
}