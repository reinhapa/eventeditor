package io.hackergarten.eventeditor.model;

import java.util.Date;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Event {
  private final StringProperty location;
  private final StringProperty title;
  private final ObjectProperty<Date> date;
  private final ObservableList<Link> achievements;
  private final ObservableList<Link> links;

  public Event() {
    location = new SimpleStringProperty();
    title = new SimpleStringProperty();
    date = new SimpleObjectProperty<>();
    achievements = FXCollections.observableArrayList();
    links = FXCollections.observableArrayList();
  }

  public StringProperty locationProperty() {
    return location;
  }

  public String getLocation() {
    return location.get();
  }

  public void setLocation(String location) {
    this.location.set(location);
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

  public ObjectProperty<Date> dateProperty() {
    return date;
  }

  public Date getDate() {
    return date.get();
  }

  public void setDate(Date date) {
    this.date.set(date);
  }

  public ObservableList<Link> achievementsProperty() {
    return achievements;
  }

  public List<Link> getAchievements() {
    return achievements;
  }

  public void setAchievements(List<Link> achievements) {
    this.achievements.setAll(achievements);
  }

  public ObservableList<Link> linksProperty() {
    return links;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links.setAll(links);
  }
}
