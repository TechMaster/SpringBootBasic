package vn.techmaster.bookstore.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.bookstore.model.Book;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class RestAPI {
  @GetMapping
  public List<Book> getAll() {
    ArrayList<Book> books = new ArrayList<>();
    books.add(new Book(1, "Good with the wind", "gone_with_the_wind.jpg"));
    books.add(new Book(2, "Nhà Giả Kim Thuật", "gia_kim_thuat.jpg"));
    books.add(new Book(3, "Harry Porter", "harry_porter.jpg"));
    books.add(new Book(4, "Deep Work", "deep_work.jpg"));
    return books;
  }
}
