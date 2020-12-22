# Delete xoá một đầu sách

Để xoá một đầu sách, chúng ta cần có id của đầu sách đó rồi tạo GET request tới đường dẫn
http://localhost:8080/book/delete/id

**Đây không phải là cách an toàn để thao tác xoá. Vì**

Lệnh xoá nằm trên URL. Người dùng hoặc hacker chỉ cần tìm được id rồi mở trình duyệt các bản ghi sẽ bị xoá. Cách đúng đắn:

1. Kiểm tra phân quyền được phép xoá hay không.
2. Sử dụng phương thức POST hoặc tốt nhất là phương thức DELETE

Trong khuôn khổ "Em tập làm khoá học", chúng ta cứ thực hiện lệnh DELETE qua phương thức GET

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
│   │   │   │   │   └── BookController.java <-- Thêm phương thức GET /book/delete/id ở đây
│   │   │   │   ├── model
│   │   │   │   │   └── Book.java
│   │   │   │   ├── repository
│   │   │   │   │   ├── BookDao.java <-- Thực hiện nốt 2 phương thức xoá
│   │   │   │   │   └── Dao.java
│   │   │   │   └── BookstoreApplication.java
├── resources
│   ├── static
│   │   └── book.csv
│   ├── templates
│   │   ├── allbooks.html
│   │   ├── book.html <-- Thêm link Delete vào đây
│   │   └── form.html
│   └── application.properties
```
## Thực hiện từng bước

1. Trong [BookController.java](src/main/java/vn/techmaster/bookstore/controller/BookController.java), thêm phương thức GET

  ```java
  @GetMapping(value = "/delete/{id}")
  public String deleteByID(@PathVariable("id") int id) {    
    bookDao.deleteByID(id);        
    return "redirect:/book"; //Xoá xong thì quay về trang http://localhost:8080/book
  }
  ```

2. Trong [book.html](src/main/resources/templates/book.html) bổ xung link Delete
  ```<a th:href="@{/book/delete/{id}(id=${book.id})}">Delete book</a><br>```
  nội dung sau khi thêm sẽ như sau:
  ```html
  <body>
  <h1 th:text="${book.title}"></h1><br>
  <p th:text="${book.description}"></p><br>
  <a th:href="@{/book/edit/{id}(id=${book.id})}">Edit book</a>&nbsp;&nbsp;
  <a th:href="@{/book/delete/{id}(id=${book.id})}">Delete book</a><br>  
  </body>
  ```

3. Chạy thử, ơ tại sao không xoá được nhỉ ?
  Hoá ra quên chưa implement nốt phương thức delete ở [BookDao.java](src/main/java/vn/techmaster/bookstore/repository/BookDao.java)

  Trong [BookDao.java](src/main/java/vn/techmaster/bookstore/repository/BookDao.java) thêm 2 phương thức:

  ```java
  @Override
  public void deleteByID(int id) {
    get(id).ifPresent(existbook -> collections.remove(existbook));
  }

  @Override
  public void delete(Book book) {
    deleteByID(book.getId());
  }
  ```
4. Chạy lại thấy đầu sách đã được xoá