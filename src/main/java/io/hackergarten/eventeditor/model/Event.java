package io.hackergarten.eventeditor.model;

import java.util.Date;
import java.util.List;

public class Event {

  private String location;
  private String title;
  private Date date;
  private List<Link> achievements;
  private List<Link> links;
  
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public List<Link> getAchievements() {
    return achievements;
  }
  public void setAchievements(List<Link> achievements) {
    this.achievements = achievements;
  }
  public List<Link> getLinks() {
    return links;
  }
  public void setLinks(List<Link> links) {
    this.links = links;
  }
}
