# Tìm kiếm theo từ khoá

Class Book có 2 trường title và description. Yêu cầu gõ vào một từ khoá, cần tìm kiếm từ khoá này không quan tâm đến chữ hoa, chữ thường (case insensitive)

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
│   │   │   │   │   └── BookController.java <-- Thêm 2 phương thức
│   │   │   │   ├── model
│   │   │   │   │   └── Book.java
│   │   │   │   ├── repository
│   │   │   │   │   ├── BookDao.java <-- Thực hiện phương thức searchByKeyword
│   │   │   │   │   └── Dao.java  <-- Bổ xung phương thức searchByKeyword
│   │   │   │   ├── request
│   │   │   │   │   └── SearchRequest.java  <-- Thêm class này để bind với Request.Body
│   │   │   │   └── BookstoreApplication.java
├── resources
│   ├── static
│   │   └── book.csv
│   ├── templates
│   │   ├── allbooks.html
│   │   ├── book.html
│   │   ├── form.html
│   │   └── search.html <-- Thêm form tìm kiếm theo keyword
│   └── application.properties
```

## Thực hành từng bước

1. Trong [Book.java](src/main/java/vn/techmaster/bookstore/model/Book.java) bổ xung phương thức này

```java
public boolean matchWithKeyword(String keyword) {
   String keywordLowerCase = keyword.toLowerCase();
   return (title.toLowerCase().contains(keywordLowerCase) || 
   description.toLowerCase().contains(keywordLowerCase));
}
```
Nếu keyword sau khi lowercase nằm trong trường title hoặc description thì trả về true.

2. Trong [Dao.java](src/main/java/vn/techmaster/bookstore/repository/Dao.java) bổ xung mẫu hàm

```java
public abstract List<T> searchByKeyword(String keyword);
```

3. Trong [BookDao.java](src/main/java/vn/techmaster/bookstore/repository/BookDao.java), thực hiện phương thức này

```java
@Override
public List<Book> searchByKeyword(String keyword) {
   //Tham khảo chi tiết ở đây nhé. Đây là Lambda Expression có từ Java 8.
   //https://www.baeldung.com/java-stream-filter-lambda  
   return collections
   .stream()
   .filter(book -> book.matchWithKeyword(keyword))
   .collect(Collectors.toList());
}
```

Mấy cái lệnh stream(), filter(), collect(), các bạn tham khảo Java 8 Lambda expression và Stream nhé. Thầy cũng chỉ dùng, chưa kịp tìm hiểu sâu. Chạy được đã !

4. Tạo [search.html](src/main/resources/templates/search.html)
```html
<form action="#" th:action="@{/book/search}" method="post"
          novalidate="novalidate" th:object="${searchrequest}" >
   <input type="text" th:field="*{keyword}" placeholder="type keyword here"/><br><br>
   <button type="submit">Search</button>
</form>
```

5. Tạo [SearchRequest.java](src/main/java/vn/techmaster/bookstore/request/SearchRequest.java) để bind keyword trong Request.Body trình duyệt gửi lên

```java
public class SearchRequest {
  private String keyword;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
```

6. Trong [BookController.java](src/main/java/vn/techmaster/bookstore/controller/BookController.java)

Bổ xung 2 phương thức
```java
@GetMapping("/search") //Hiển thị form tìm kiếm
public String searchForm(Model model) { 
   model.addAttribute("searchrequest", new SearchRequest());   
   return "search";
}
  

@PostMapping("/search")
public String searchByKeyword(@ModelAttribute SearchRequest request, BindingResult bindingResult, Model model) {
   if (!bindingResult.hasFieldErrors()) {
      model.addAttribute("books", bookDao.searchByKeyword(request.getKeyword()));
   }    
   return "allbooks";
}
```

Khi ```bindingResult.hasFieldErrors()``` không có lỗi, có nghĩa trường keyword trong form [search.html](src/main/resources/templates/search.html) đã được chuyển đổi thành công vào đối tượng [SearchRequest.java](src/main/java/vn/techmaster/bookstore/request/SearchRequest.java)

Danh sách các đầu sách chứa từ khoá sẽ được thêm vào model với key là "books"
