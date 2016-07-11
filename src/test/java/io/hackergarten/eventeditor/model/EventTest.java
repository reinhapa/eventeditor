package io.hackergarten.eventeditor.model;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class EventTest {

  @Test
  public void test() throws Exception {
    try (InputStream in = getClass().getResourceAsStream("events.json")) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      List<Event> events = mapper.readValue(in,
          mapper.getTypeFactory().constructCollectionType(List.class, Event.class));
      assertEquals(2, events.size());
    }
  }

}
