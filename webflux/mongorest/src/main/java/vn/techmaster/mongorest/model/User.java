package vn.techmaster.mongorest.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

  @Id
  private String userId;
  private String name;
  private Date creationDate = new Date();
  private Map<String, String> userSettings = new HashMap<>();

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Map<String, String> getUserSettings() {
    return userSettings;
  }

  public void setUserSettings(Map<String, String> userSettings) {
    this.userSettings = userSettings;
  }
}
