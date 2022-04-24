package com.ronanclancy.webapplicationA.client;

import com.ronanclancy.webapplicationA.dto.EventResponse;
import com.ronanclancy.webapplicationA.utils.LogStackUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Log4j2
public class EventClient {

  private final WebClient eventServiceClient;

  @Autowired
  public EventClient(WebClient eventServiceClient) {
    this.eventServiceClient = eventServiceClient;
  }

  public EventResponse event(final String eventId) {
    LogStackUtils.logInfo("Calling service with request " + eventId);
    return eventServiceClient
        .get()
        .uri("/event/" + eventId)
        .retrieve()
        .bodyToMono(EventResponse.class)
        .block();
  }
}
