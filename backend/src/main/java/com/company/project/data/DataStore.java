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
      DOMAIN.MEDIA, List.of(
          new ExtractPayload("https://www.wirtualnemedia.pl/wyniki?zapytanie=telekomunikacja")
      ),
      DOMAIN.LAW, List.of(
          new ExtractPayload("https://bip.uke.gov.pl")
      ),
      DOMAIN.COMPANY_STATUS, List.of(
          new ExtractPayload("https://telko.in")
      ),
      DOMAIN.MARKETING, List.of(
          new ExtractPayload("https://mmponline.pl/szukaj/?OK=suchen&OK=suchen&i_sortfl=pubdate&i_sortd=desc&i_q=telekomunikacja")
      )
  );

  private final String site_extraction_prompt = """
      Extract the important/recent fact points / news from this site %s for domain %s.
      Return up to 10 of them in the following json format:
       [
         {
           url = "${point of interest url, if partial reconstruct from original}"
           title = "${poi title}"
           content = "${content}"
           importance = ${1-10 level of should a person take action because of this}
         },
         {
         ...
         }
         ...
        ]
      """;

  private final Map<DOMAIN, String> prompts = Map.of(
      DOMAIN.LAW, """
          "Analyze and decode this legal feed. Keep it serious, formal, and professional. I need it to be concise yet informing."
          Constrain the return to a paragraph.
          TITLE: %s
          CONTENT: %s
          """,
      DOMAIN.COMPANY_STATUS, """
          "Analyze and comment on the significance of actions taken, point out the potential risks and benefits."
           Constrain the return to a paragraph.
          TITLE: %s
          CONTENT: %s
          """,
      DOMAIN.MEDIA, """
          "Give me the summary of this feed, keep it concise and only point out the important parts"
          Constrain the return to a paragraph.
          TITLE: %s
          CONTENT: %s
          """,
      DOMAIN.MARKETING, """
          "Unleash your inner marketing guru on this. I want a summary that's dripping with buzzwords and corporate jargon. Talk to me about synergy, paradigm shifts, and leveraging assets. Make me believe this is the most revolutionary thing since sliced bread, or the biggest flop since the Zune."
          TITLE: %s
          CONTENT: %s
          """
  );

  public String prepare_prompt(String s, Headline headline) {
    return s.formatted(headline.title(), headline.content());
  }
}