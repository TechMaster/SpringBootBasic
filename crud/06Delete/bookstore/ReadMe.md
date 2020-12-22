# Edit/Update sửa nội dung một đầu sách sẵn có

Để sửa một đầu sách sẵn có, chúng ta cần có id của đầu sách đó, tiếp đến lấy dữ liệu đầu sách đổ vào một form. Người dùng sửa đổi, rồi submit

## Cấu trúc thư mục

## Thực hành từng bước

1. Trong [BookDao.java](src/main/java/vn/techmaster/bookstore/repository/BookDao.java) bổ xung phương thức
  ```java
  @Override
  public void update(Book book) {
    get(book.getId()).ifPresent(existbook -> {
      existbook.setTitle(book.getTitle());
      existbook.setDescription(book.getDescription());
    });
  }
  ```
2. Trong [book.html](src/main/resources/templates/book.html) thêm link để trỏ sang form edit
```html
<body>
  <h1 th:text="${book.title}"></h1><br>
  <p th:text="${book.description}"></p><br>
  <a th:href="@{/book/edit/{id}(id=${book.id})}">Edit book</a> <!-- Thêm link Edit -->
</body>
```

3. Trong [BookController.java](src/main/java/vn/techmaster/bookstore/controller/BookController.java) thêm phương thức hứng GET request ở ```/book/edit/id```
```java
@GetMapping(value = "/edit/{id}")
public String editBookId(@PathVariable("id") int id, Model model) {    
  Optional<Book> book = bookDao.get(id);
  if (book.isPresent()) {
    model.addAttribute("book", book.get());
  } 
  return "form";
}
```
Giải thích:
- ```value = "/edit/{id}"``` đây là đường dẫn có tham số id động ở cuối cùng
- ```@PathVariable("id") int id``` hãy lấy tham số id trên đường dẫn truyền vào biến ```int id```
- ```Optional<Book>```: khai báo biến có thể null
- ```book.isPresent()```: nếu biến book không null
- ```book.get()```: unwrap biến kiểu Optional

4. Trong chức năng Edit, chúng ta vẫn dùng lại [form.html](src/main/resources/templates/form.html), tuy nhiên có sự khác biệt với khi tạo mới đầu sách.
- Khi Add New, đối tượng book truyền vào form.html được khởi tạo bằng defaul constructor. Các thuộc tính là rỗng, id = 0.
- Khi Edit, đối tượng book có các trường chứa dữ liệu thực sự, id khác 0. Chúng ta phải cần id khác 0 để sau khi sửa lại POST thông tin sửa đổi lên server

5. Trong [form.html](src/main/resources/templates/form.html), thêm một trường ẩn (hidden field)
```<input type="hidden" placeholder="id" th:field="*{id}"/>```. Trường này lưu book.id, chúng ta để nó ẩn vì không muốn người dùng cuối tuỳ tiện sửa id của đầu sách.

```html
<form action="#" th:action="@{/book/save}" th:object="${book}" method="post"
          novalidate="novalidate">
      <input type="hidden" placeholder="id" th:field="*{id}"/><br><br>
      <input type="text" placeholder="title" th:field="*{title}"/><br><br>
      <input type="text" placeholder="description" th:field="*{description}"/><br><br>
      <button type="submit">Save</button>
</form>
```

6. Trong [BookController.java](src/main/java/vn/techmaster/bookstore/controller/BookController.java)
cần sửa lại logic của phương thức save để phân biệt 2 trường hợp:
  - Add New: ```book.id == 0```
  - Edit Update: ```book.id > 0```
  
```java
@PostMapping("/save") // Hứng POST request gọi đến /book/save
public String save(Book book, BindingResult result) {
  if (result.hasErrors()) {
    return "form";
  }
  bookDao.add(book);
  return "redirect:/book"; // Chuyển về đường dẫn /book
}
```

thành

```java
@PostMapping("/save")
public String save(Book book, BindingResult result, RedirectAttributes redirect) {
  if (result.hasErrors()) {
    return "form";
  }
  if (book.getId() > 0) { //Nếu có trường id có nghĩa là đây là edit form
    bookDao.update(book);
  } else { //Nếu id ==0 có nghĩa book lần đầu được add new
    bookDao.add(book);
  }
      
  return "redirect:/book";
}
```

7. Biên dịch và chạy thử: thêm sau đó sửa đổi một số đầu sách. 
