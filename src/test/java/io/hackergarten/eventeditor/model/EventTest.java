package io.hackergarten.eventeditor.model;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the basic functionality of the {@link Event} implementation.
 */
public class EventTest {
  private Event event;

  @BeforeEach
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
      assertThat(events).hasSize(2);
    }
  }

  @Test
  public void testTitleProperties() {
    String expected = "the title value";
    assertThat(event.getTitle()).isNull();
    StringProperty property = event.titleProperty();
    assertThat(property).isNotNull();
    assertThat(property.get()).isNull();
    event.setTitle(expected);
    assertThat(event.getTitle()).isEqualTo(expected);
    assertThat(property.get()).isEqualTo(expected);
  }

  @Test
  public void testVenueProperties() {
    String expected = "the venue value";
    assertThat(event.getVenue()).isNull();
    StringProperty property = event.venueProperty();
    assertThat(property).isNotNull();
    assertThat(property.get()).isNull();
    event.setVenue(expected);
    assertThat(event.getVenue()).isEqualTo(expected);
    assertThat(property.get()).isEqualTo(expected);
  }

  @Test
  public void testLocationProperties() {
    String expected = "the location value";
    assertThat(event.getLocation()).isNull();
    StringProperty property = event.locationProperty();
    assertThat(property).isNotNull();
    assertThat(property.get()).isNull();
    event.setLocation(expected);
    assertThat(event.getLocation()).isEqualTo(expected);
    assertThat(property.get()).isEqualTo(expected);
  }

  @Test
  public void testDetailsProperties() {
    String expected = "the details value";
    assertThat(event.getDetails()).isNull();
    StringProperty property = event.detailsProperty();
    assertThat(property).isNotNull();
    assertThat(property.get()).isNull();
    event.setDetails(expected);
    assertThat(event.getDetails()).isEqualTo(expected);
    assertThat(property.get()).isEqualTo(expected);
  }

  @Test
  public void testDateProperties() {
    LocalDate expected = LocalDate.now();
    assertThat(event.getDate()).isNull();
    ObjectProperty<LocalDate> property = event.dateProperty();
    assertThat(property).isNotNull();
    assertThat(property.get()).isNull();
    event.setDate(expected);
    assertThat(event.getDate()).isEqualTo(expected);
    assertThat(property.get()).isEqualTo(expected);
  }

  @Test
  public void testAchievementsProperties() {
    List<Link> expected = Collections.singletonList(new Link());
    assertThat(event.getAchievements()).isNull();
    Property<ObservableList<Link>> property = event.achievementsProperty();
    assertThat(property).isNotNull();
    assertThat(property.getValue()).isNull();
    event.setAchievements(expected);
    assertThat(event.getAchievements()).isEqualTo(expected);
    assertThat(property.getValue()).isEqualTo(expected);
  }

  @Test
  public void testLinksProperties() {
    List<Link> expected = Collections.singletonList(new Link());
    assertThat(event.getLinks()).isNull();
    Property<ObservableList<Link>> property = event.linksProperty();
    assertThat(property).isNotNull();
    assertThat(property.getValue()).isNull();
    event.setLinks(expected);
    assertThat(event.getLinks()).isEqualTo(expected);
    assertThat(property.getValue()).isEqualTo(expected);
  }
}
