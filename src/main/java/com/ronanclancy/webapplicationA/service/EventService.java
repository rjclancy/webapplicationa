package com.ronanclancy.webapplicationA.service;

import com.ronanclancy.webapplicationA.client.EventClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EventService {

  @Autowired
  private EventClient eventClient;

  public String getResults(Long eventId) throws Exception {
    log.info("In Event Service eventId " + eventId);
    try {
      log.info("Calling Client Service for event " + eventId);
      eventClient.event(String.valueOf(eventId));
    } catch (RuntimeException e) {
      log.error("Something went wrong in webapplicationa getting event " + eventId, e);
      throw new RuntimeException();
    }
    return "event_result";
  }
}
