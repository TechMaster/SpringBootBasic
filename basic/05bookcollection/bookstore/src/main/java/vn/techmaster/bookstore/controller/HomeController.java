package vn.techmaster.bookstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import vn.techmaster.bookstore.model.Book;

@Controller
public class HomeController {
  // Đọc giá trị từ application.properties
  @Value("${spring.application.name}")
  private String appName;
  static final String APP_NAME = "appName";


  @GetMapping(value = "/")
  public String getHome(Model model) {
    model.addAttribute(APP_NAME, appName);
    return "home";
  }


  // Trả về books là mảng của String
  @GetMapping(value = "/book")
  public String getBook(Model model) {
    String[] bookCollection ={"Deep Work","Nhà Giả Kim ","Cafe cùng Tony","Tôi đi code dạo"};
    model.addAttribute("books", bookCollection);
    model.addAttribute(APP_NAME, appName);
    return "book";
  }

  // Trả về books là mảng của đối tượng kiểu Book
  @GetMapping(value = "/book2")
  public String getBook2(Model model) {
    Book[] bookCollection = { 
      new Book(1, "Cafe cùng Tony", "Tony"),
      new Book(2, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài")
    };

    model.addAttribute("books", bookCollection);
    model.addAttribute(APP_NAME, appName);
    return "book2";
  }
}