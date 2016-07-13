package io.hackergarten.eventeditor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 * Tests the basic functionality of the {@link Link} implementation.
 */
public class LinkTest {
  private Link link;

  @Before
  public void setUp() {
    link = new Link();
  }

  @Test
  public void testTitleProperties() {
    String expected = "the title value";
    assertNull(link.getTitle());
    StringProperty property = link.titleProperty();
    assertNotNull(property);
    assertNull(property.get());
    link.setTitle(expected);
    assertEquals(expected, link.getTitle());
    assertEquals(expected, property.get());
  }

  @Test
  public void testUrlProperties() throws Exception {
    URL expected = new URL("file:/somelocation");
    assertNull(link.getUrl());
    ObjectProperty<URL> property = link.urlProperty();
    assertNotNull(property);
    assertNull(property.get());
    link.setUrl(expected);
    assertEquals(expected, link.getUrl());
    assertEquals(expected, property.get());
  }
}
