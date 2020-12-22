# Xem chi tiết nội dung một đầu sách

Ở bài 01, chúng ta đã liệt kê các đầu sách, giờ chúng ta sẽ bổ xung link để mở ra trang chi tiết xem từng đầu sách một. Chúng ta dùng hyperlink có cấu trúc như sau

```<a href="/book/id>Book title</a>```

id chính là thuộc tính id  xác đính duy nhất đối tượng Book
## Cấu trúc thư mục

```
.
├── java
│   ├── vn
│   │   ├── techmaster
│   │   │   ├── bookstore
│   │   │   │   ├── config
│   │   │   │   │   └── RepoConfig.java
│   │   │   │   ├── controller
│   │   │   │   │   └── BookController.java <-- Thêm phương thức hứng GET request /book/id
│   │   │   │   ├── model
│   │   │   │   │   └── Book.java
│   │   │   │   ├── repository
│   │   │   │   │   ├── BookDao.java <-- Thêm phương thức để tìm đối tượng Book theo id
│   │   │   │   │   └── Dao.java
│   │   │   │   └── BookstoreApplication.java
├── resources
│   ├── static
│   │   └── book.csv
│   ├── templates
│   │   ├── allbooks.html  <-- Bổ xung hyperlink đến /book/id
│   │   └── book.html  <-- Mới thêm để xem chi tiết một đầu sách
│   └── application.properties
```

## Thực hành từng bước

1. Thực hiện phương thức get(int id)

Trong [BookDao.java](src/main/java/vn/techmaster/bookstore/repository/BookDao.java), chúng ta cần thêm phương thức để lấy ra một đối tượng Book trong collections theo id.
```java
@Override
public Optional<Book> get(int id) {
  return collections.stream().filter(u -> u.getId() == id).findFirst();
}
```

Chú ý hàm này viết theo phong cách Lambda Expression của Java 8. Biểu thức trong filter hiểu là khi duyệt qua từng phần tử u, hãy kiểm tra xem ```u.getId() == id``` nếu bằng nhau thì lấy luôn phần tử đầu tiên ```.findFirst()```

2. Thêm phương thức trong [BookController.java](src/main/java/vn/techmaster/bookstore/controller/BookController.java) để hứng request tới đường dẫn ```<a href="/book/id>Book title</a>```

```java
@GetMapping(value = "/{id}")
public String getByID(@PathVariable("id") int id, Model model) {
  Optional<Book> book = bookDao.get(id);
  if (book.isPresent()) {
    model.addAttribute("book", book.get());
  }    
  return "book";
}
```

3. Bổ xung file template [book.html](src/main/resources/templates/book.html)
```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title th:text="${book.title}></title>
</head>
<body>
  <h1 th:text="${book.title}"></h1><br>
  <p th:text="${book.description}"></p><br>
</body>
</html>
```

4. Chỉnh lại template [allbooks.html](src/main/resources/templates/allbooks.html) từ
```html
<ul>
    <li th:each="book: ${books}">
      <strong th:text="${book.title}"></strong><br>
      <p th:text="${book.description}"></p>
    </li>
</ul>
```
thành
```html
<ul>
    <li th:each="book: ${books}">
      <a th:href="@{/book/{id}(id=${book.id})}"><strong th:text="${book.title}"></strong></a><br>
      <p th:text="${book.description}"></p>
    </li>
</ul>
```

