package vn.techmaster.demobean.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.demobean.entity.Book;
/*

Thí nghiệm với annotation @CrossOrigin
1. Cài đặt Node.js
2. Cài đặt npm install --global http-server
3. Chạy ứng dụng demobean, truy cập vào http://localhost:8080/book bằng trình duyệt để thấy json trả về
3. cd simpleweb
4. http-server -p 8081
5. mở trình duyệt ở cổng 8081 http://localhost:8081, inspect để xem console

Nếu không có annotation @CrossOrigin, simpleweb không thể gọi được vào http://localhost:8080

*/
@RestController
public class APIController {
  @CrossOrigin()
  @GetMapping("/book")
  public List<Book> getAll() {
    ArrayList<Book> books = new ArrayList<>();
    books.add(new Book(1, "Good with the wind", "gone_with_the_wind.jpg"));
    books.add(new Book(2, "Nhà Giả Kim Thuật", "gia_kim_thuat.jpg"));
    books.add(new Book(3, "Harry Porter", "harry_porter.jpg"));
    books.add(new Book(4, "Deep Work", "deep_work.jpg"));
    return books;
  }  
}
