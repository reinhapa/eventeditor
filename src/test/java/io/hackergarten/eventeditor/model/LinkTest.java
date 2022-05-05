package io.hackergarten.eventeditor.model;

import java.net.URL;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the basic functionality of the {@link Link} implementation.
 */
class LinkTest {
  private Link link;

  @BeforeEach
  void setUp() {
    link = new Link();
  }

  @Test
  void testTitleProperties() {
    String expected = "the title value";
    assertThat(link.getTitle()).isNull();
    StringProperty property = link.titleProperty();
    assertThat(property).isNotNull();
    assertThat(property.get()).isNull();
    link.setTitle(expected);
    assertThat(link.getTitle()).isEqualTo(expected);
    assertThat(property.get()).isEqualTo(expected);
  }

  @Test
  void testUrlProperties() throws Exception {
    URL expected = new URL("file:/somelocation");
    assertThat(link.getUrl()).isNull();
    ObjectProperty<URL> property = link.urlProperty();
    assertThat(property).isNotNull();
    assertThat(property.get()).isNull();
    link.setUrl(expected);
    assertThat(link.getUrl()).isEqualTo(expected);
    assertThat(property.get()).isEqualTo(expected);
  }
}
