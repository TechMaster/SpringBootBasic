# Thử dùng tính năng mới Records trong Java 14 thay thế cho Class
Trong ví dụ này chúng ta sử dụng tính năng mới nhất của Java 14-15 đó là records.
Muốn dùng records thì phải chọn JDK 14 hoặc tốt hơn là JDK 15 và bật --enable-preview
Thay vì viết một đoạn code dài như thế này:
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
}
```

thì chỉ cần khai báo ngắn gọn thế này:
```java
public record Book(int id, String title, String coverPhoto) {
};
```

Trong file pom.xml
```xml
	<properties>
		<java.version>15</java.version>
	</properties>
```

tiếp đến bổ xung lựa chọn --enable-preview vào maven-compiler-plugin
```xml
<build>
		<pluginManagement>
			<plugins>
				<plugin>
					<artifactId>maven-compiler-plugin</artifactId>
					<configuration>
						<release>15</release>
						<compilerArgs>--enable-preview</compilerArgs>
					</configuration>
				</plugin>		
			</plugins>
		</pluginManagement>
  </build>
  ```

# Chạy tốt khi render HTML View
Records trả về cho Thymeleaf view chạy tốt, chỉ cần điều chỉnh cú pháp truy vấn thuộc tính
```book.id``` sang ```book.id()```

```html
<ul th:each="book : ${books}"> <li th:text="${book.id} + ' - ' + ${book.title}"></li></ul>
```

```html
<ul th:each="book : ${books}"> <li th:text="${book.id()} + ' - ' + ${book.title()}"></li></ul>
```

# Nhưng thất bại khi render JSON
Hiện chưa có thư viện nào serialize tốt đối tượng records
Đoạn code dưới đây chỉ trả ```[{},{},{},{}]```

```java
package vn.techmaster.bookstore;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class RestAPI {
  @GetMapping
  public String getAll() {
    List<Book> books = new ArrayList<>();
    books.add(new Book(1, "Good with the wind", "gone_with_the_wind.jpg"));
    books.add(new Book(2, "Nhà Giả Kim Thuật", "gia_kim_thuat.jpg"));
    books.add(new Book(3, "Harry Porter", "harry_porter.jpg"));
    books.add(new Book(4, "Deep Work", "deep_work.jpg"));

    // Create Jsonb and serialize
    Jsonb jsonb = JsonbBuilder.create();
    String result = jsonb.toJson(books);
    try {
      jsonb.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
```

# Kết luận
Records là một tính năng hay, cho phép rút gọn hơn nhiều so với khai báo class thông thường. Tuy nhiên Records lại chưa hỗ trợ tốt việc serialize ra định dạng JSON.