package vn.techmaster.demobean.entity;

public class Book {
  private int id;
  private String title;
  private String cover;

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

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cover == null) ? 0 : cover.hashCode());
    result = prime * result + id;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Book other = (Book) obj;
    if (cover == null) {
      if (other.cover != null)
        return false;
    } else if (!cover.equals(other.cover))
      return false;
    if (id != other.id)
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }

  public Book(int id, String title, String cover) {
    this.id = id;
    this.title = title;
    this.cover = cover;
  }
}
