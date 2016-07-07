package io.hackergarten.eventeditor.model;

import static java.nio.file.Files.exists;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class EventListTest {
  private EventList eventList;

  @Before
  public void setUp() {
    eventList = new EventList();
  }


  @Test
  public void testLoadPath() throws Exception {
    Path eventFile = Paths.get(System.getProperty("user.home")).resolve("git/hackergarten.github.io/events.json");
    if (exists(eventFile)) {
      eventList.load(eventFile);
    }
  }

  @Test
  public void testLoadInputStream() throws Exception {
    try (InputStream in = getClass().getResourceAsStream("events.json")) {
      eventList.load(in);
      assertEquals(2, eventList.size());
    }
  }

  @Test
  public void testAddGet() {
    Event event1 = new Event();
    Event event2 = new Event();
    assertTrue(eventList.add(event1));
    assertTrue(eventList.add(event2));
    assertSame(event1, eventList.get(0));
    assertSame(event2, eventList.get(1));
  }

  @Test
  public void testSetRemove() {
    Event event1 = new Event();
    Event event2 = new Event();

    eventList.add(event1);
    assertSame(event1, eventList.set(0, event2));
    assertSame(event2, eventList.remove(0));
  }
}
