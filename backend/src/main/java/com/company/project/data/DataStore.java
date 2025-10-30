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
          "Decode this legal mumbo jumbo for me, but explain it like I'm a five-year-old who's also a mob boss. Keep it serious, formal, and cold. I need the bottom line, fast."
          Constrain the return to a paragraph.
          TITLE: %s
          CONTENT: %s
          """,
      DOMAIN.COMPANY_STATUS, """
          "Gimme the dirt. Is this company printing money or circling the drain? I want the juicy details, the kind of stuff they'd whisper in a smoky backroom. Spill it.
           Do this but not with 'PLAY', they are the best and all fluff.
           Constrain the return to a paragraph.
          TITLE: %s
          CONTENT: %s
          """,
      DOMAIN.MEDIA, """
          "My brain is basically TikTok at this point. Give me the summary in 10 seconds or less. I want the highlights, the drama, the 'OMG, what?!' moments. Don't bore me with the details."
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