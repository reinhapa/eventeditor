package io.hackergarten.eventeditor.model;

import static java.nio.file.Files.newInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javafx.collections.ModifiableObservableListBase;

/**
 * Represents a list of all {@link Event} objects.
 */
public final class EventList extends ModifiableObservableListBase<Event> {
  private final List<Event> backingList;

  public EventList() {
    backingList = new ArrayList<>();
  }

  public void load(Path eventFile) throws JsonParseException, JsonMappingException, IOException {
    try (InputStream in = newInputStream(eventFile)) {
      load(in);
    }
  }

  public void load(InputStream in) throws JsonParseException, JsonProcessingException, IOException {
    clear();
    JsonFactory factory = new JsonFactory();
    try (JsonParser jp = factory.createParser(in)) {
      // advance stream to START_ARRAY first:
      jp.nextToken();
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      // and then each time, advance to opening START_OBJECT
      while (jp.nextToken() == JsonToken.START_OBJECT) {
        add(mapper.readValue(jp, Event.class));
      }
    }
  }

  @Override
  public Event get(int index) {
    return backingList.get(index);
  }

  @Override
  public int size() {
    return backingList.size();
  }

  @Override
  protected void doAdd(int index, Event element) {
    backingList.add(index, element);
  }

  @Override
  protected Event doSet(int index, Event element) {
    return backingList.set(index, element);
  }

  @Override
  protected Event doRemove(int index) {
    return backingList.remove(index);
  }
}
