package vn.techmaster.bookstore.model;

public class Book {
  int id;
  String title;
  String coverPhoto;

  public Book(int id, String title, String coverPhoto) {
    this.id = id;
    this.title = title;
    this.coverPhoto = coverPhoto;
  }

  // Nếu không có getter/setter thì sẽ lỗi ngay đấy.
  public long getId() {
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
}