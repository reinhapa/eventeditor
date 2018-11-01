package io.hackergarten.eventeditor.model;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Specifies all properties for a single event.
 * 
 * @author Patrick Reinhart
 */
public final class Event {
  @Deprecated
  private final StringProperty title;  // old value: calculated: venue + ", " + address
  private final StringProperty address;
  @Deprecated
  private final StringProperty location;
  private final StringProperty venue;
  private final StringProperty details;
  private final ObjectProperty<LocalDate> date;
  private final ObjectProperty<ObservableList<Link>> achievements;
  private final ObjectProperty<ObservableList<Link>> links;

  public Event() {
    title = new SimpleStringProperty();
    address = new SimpleStringProperty();
    location = new SimpleStringProperty();
    venue = new SimpleStringProperty();
    details = new SimpleStringProperty();
    date = new SimpleObjectProperty<>();
    achievements = new SimpleObjectProperty<>();
    links = new SimpleObjectProperty<>();
  }

  public StringProperty addressProperty() {
    return address;
  }

  public String getAddress() {
    return address.get();
  }

  public void setAddress(String address) {
    this.address.set(address);
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

  public StringProperty venueProperty() {
    return venue;
  }

  public String getVenue() {
    return venue.get();
  }

  public void setVenue(String venue) {
    this.venue.set(venue);
  }

  public StringProperty detailsProperty() {
    return details;
  }

  public String getDetails() {
    return details.get();
  }

  public void setDetails(String details) {
    this.details.set(details);
  }

  public ObjectProperty<LocalDate> dateProperty() {
    return date;
  }

  public LocalDate getDate() {
    return date.get();
  }

  public void setDate(LocalDate date) {
    this.date.set(date);
  }

  public Property<ObservableList<Link>> achievementsProperty() {
    return achievements;
  }

  public List<Link> getAchievements() {
    return achievements.get();
  }

  public void setAchievements(List<Link> achievements) {
    this.achievements.set(FXCollections.observableArrayList(achievements));
  }

  public Property<ObservableList<Link>> linksProperty() {
    return links;
  }

  public List<Link> getLinks() {
    return links.get();
  }

  public void setLinks(List<Link> links) {
    this.links.set(FXCollections.observableArrayList(links));
  }
}
