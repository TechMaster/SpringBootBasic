Ví dụ này demo mấy kỹ thuật:
- Trả về một collection các Object ```ArrayList<Book> books = new ArrayList<>();```
- Khai báo Class chứa các thuộc tính
- Cú pháp Thymeleaf

Trong ví dụ này tôi khai báo class Book theo cú pháp Java cổ điển
```java
public class Book {
  int id;
  String title;
  String coverPhoto;

  Book(int id, String title, String coverPhoto) {
    this.id = id;
    this.title = title;
    this.coverPhoto = coverPhoto;
  }
}
```