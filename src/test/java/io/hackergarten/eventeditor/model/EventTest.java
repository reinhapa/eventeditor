package io.hackergarten.eventeditor.model;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Tests the basic functionality of the {@link Event} implementation.
 */
public class EventTest {
  private Event event;

  @Before
  public void setUp() {
    event = new Event();
  }

  @Test
  public void testReadFromJson() throws Exception {
    try (InputStream in = getClass().getResourceAsStream("events.json")) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      List<Event> events = mapper.readValue(in,
          mapper.getTypeFactory().constructCollectionType(List.class, Event.class));
      assertEquals(2, events.size());
    }
  }

  @Test
  public void testTitleProperties() {
    String expected = "the title value";
    assertNull(event.getTitle());
    StringProperty property = event.titleProperty();
    assertNotNull(property);
    assertNull(property.get());
    event.setTitle(expected);
    assertEquals(expected, event.getTitle());
    assertEquals(expected, property.get());
  }

  @Test
  public void testVenueProperties() {
    String expected = "the venue value";
    assertNull(event.getVenue());
    StringProperty property = event.venueProperty();
    assertNotNull(property);
    assertNull(property.get());
    event.setVenue(expected);
    assertEquals(expected, event.getVenue());
    assertEquals(expected, property.get());
  }

  @Test
  public void testLocationProperties() {
    String expected = "the location value";
    assertNull(event.getLocation());
    StringProperty property = event.locationProperty();
    assertNotNull(property);
    assertNull(property.get());
    event.setLocation(expected);
    assertEquals(expected, event.getLocation());
    assertEquals(expected, property.get());
  }

  @Test
  public void testDetailsProperties() {
    String expected = "the details value";
    assertNull(event.getDetails());
    StringProperty property = event.detailsProperty();
    assertNotNull(property);
    assertNull(property.get());
    event.setDetails(expected);
    assertEquals(expected, event.getDetails());
    assertEquals(expected, property.get());
  }

  @Test
  public void testDateProperties() {
    LocalDate expected = LocalDate.now();
    assertNull(event.getDate());
    ObjectProperty<LocalDate> property = event.dateProperty();
    assertNotNull(property);
    assertNull(property.get());
    event.setDate(expected);
    assertEquals(expected, event.getDate());
    assertEquals(expected, property.get());
  }

  @Test
  public void testAchievementsProperties() {
    List<Link> expected = Collections.singletonList(new Link());
    assertNull(event.getAchievements());
    Property<ObservableList<Link>> property = event.achievementsProperty();
    assertNotNull(property);
    assertNull(property.getValue());
    event.setAchievements(expected);
    assertEquals(expected, event.getAchievements());
    assertEquals(expected, property.getValue());
  }

  @Test
  public void testLinksProperties() {
    List<Link> expected = Collections.singletonList(new Link());
    assertNull(event.getLinks());
    Property<ObservableList<Link>> property = event.linksProperty();
    assertNotNull(property);
    assertNull(property.getValue());
    event.setLinks(expected);
    assertEquals(expected, event.getLinks());
    assertEquals(expected, property.getValue());
  }
}
