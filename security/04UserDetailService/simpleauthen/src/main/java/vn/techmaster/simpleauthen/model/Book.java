package vn.techmaster.simpleauthen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book {
  @JsonIgnore
  int id;
  
  String title;
  String description;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean matchWithKeyword(String keyword) {
    String keywordLowerCase = keyword.toLowerCase();
    return (title.toLowerCase().contains(keywordLowerCase) || 
      description.toLowerCase().contains(keywordLowerCase));
  }
}
