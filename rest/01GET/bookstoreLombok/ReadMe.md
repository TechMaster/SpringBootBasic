# Sử dụng thư viện Lombok để rút ngắn cú pháp khai báo

## Cú pháp khai báo class Java cổ điển
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

  public String getCoverPhoto() {
    return coverPhoto;
  }

  public void setCoverPhoto(String coverPhoto) {
    this.coverPhoto = coverPhoto;
  }
}
```

## Cú pháp annotation của Lombok
```java
import lombok.Data;

@Data
public class Book {
  private final int id;
  private final String title;
  private final String coverPhoto;
}
```

Ý nghĩa của @Data annotation như sau:

@Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together: In other words, @Data generates all the boilerplate that is normally associated with simple POJOs (Plain Old Java Objects) and beans: getters for all fields, setters for all non-final fields, and appropriate toString, equals and hashCode implementations that involve the fields of the class, and a constructor that initializes all final fields, as well as all non-final fields with no initializer that have been marked with @NonNull, in order to ensure the field is never null.