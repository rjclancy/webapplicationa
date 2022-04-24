package com.ronanclancy.webapplicationA.rest;

import com.ronanclancy.webapplicationA.service.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Log4j2
public class EventController {

  private final EventService baseService;

  @GetMapping("/event/{id}")
  ResponseEntity<String> getResults(@PathVariable final Long id) throws Exception {
    log.info("getting results for event " + id);
    try {
      return new ResponseEntity<>(baseService.getResults(id), HttpStatus.OK);
    } catch (Exception e) {
      log.info("error getting results for event " + id);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
