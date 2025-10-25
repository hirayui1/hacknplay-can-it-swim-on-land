package com.company.project.services;

import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.google.genai.types.ThinkingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AiService {

  public String ruin(String input) {
    String apiKey = System.getenv("AI_API_KEY");
    Client client = Client.builder().apiKey(apiKey).build();
    String model = "gemini-2.5-flash-lite";
    List<Content> contents = List.of(
        Content.builder()
            .role("user")
            .parts(List.of(
                Part.fromText(input)
            ))
            .build()
    );
    GenerateContentConfig config =
        GenerateContentConfig
            .builder()
            .thinkingConfig(
                ThinkingConfig
                    .builder()
                    .thinkingBudget(512)
                    .build()
            )
            .build();
    String f = "";
    try {
      ResponseStream<GenerateContentResponse> responseStream = client.models.generateContentStream(model, contents, config);
      for (GenerateContentResponse res : responseStream) {
        if (res.candidates().isEmpty() || res.candidates().get().getFirst().content().isEmpty() || res.candidates().get()
            .getFirst().content().get().parts().isEmpty()) {
          continue;
        }
        List<Part> parts = res.candidates().get().getFirst().content().get().parts().get();
        for (Part part : parts) {
          f = f.concat("\n\n\n").concat(part.text().orElse(""));
        }
      }
      responseStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return f;
  }
}