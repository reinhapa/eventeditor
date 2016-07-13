package io.hackergarten.eventeditor.model;

import java.net.URL;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Specifies a link to further information with an additional URL.
 * 
 * @author Patrick Reinhart
 */
public final class Link {
  private final StringProperty title;
  private final ObjectProperty<URL> url;

  public Link() {
    title = new SimpleStringProperty();
    url = new SimpleObjectProperty<>();
  }

  public StringProperty titleProperty() {
    return title;
  }

  public String getTitle() {
    return title.get();
  }

  public void setTitle(String title) {
    this.title.set(title);
  }

  public ObjectProperty<URL> urlProperty() {
    return url;
  }

  public URL getUrl() {
    return url.get();
  }

  public void setUrl(URL url) {
    this.url.set(url);
  }
}
