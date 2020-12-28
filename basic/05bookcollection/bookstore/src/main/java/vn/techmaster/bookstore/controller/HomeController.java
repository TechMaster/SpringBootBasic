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
  static final String BOOKS = "books";

  @GetMapping(value = "/")
  public String getHome(Model model) {
    model.addAttribute(APP_NAME, appName);
    return "home";
  }

  @GetMapping(value = "/about")
  public String getAbout(Model model) {
    model.addAttribute(APP_NAME, appName);
    return "about";
  }


  // Trả về books là mảng của String
  @GetMapping(value = "/book")
  public String getBook(Model model) {
    String[] bookCollection ={"Deep Work","Nhà Giả Kim ","Cafe cùng Tony","Tôi đi code dạo"};
    model.addAttribute(BOOKS, bookCollection);
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

    model.addAttribute(BOOKS, bookCollection);
    model.addAttribute(APP_NAME, appName);
    return "book2";
  }

  // Trả về books là mảng của đối tượng kiểu Book, thêm ảnh cho từng sách
  @GetMapping(value = "/book3")
  public String getBook3(Model model) {
    Book[] bookCollection = { 
      new Book(1, "Cafe cùng Tony", "Tony"),
      new Book(2, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài")
    };

    model.addAttribute(BOOKS, bookCollection);
    model.addAttribute(APP_NAME, appName);
    return "book3";
  }

  // Trả về books là mảng của đối tượng kiểu Book, thêm ảnh cho từng sách, CSS đẹp
  @GetMapping(value = "/book4")
  public String getBook4(Model model) {
    Book[] bookCollection = { 
      new Book(1, "Cafe cùng Tony", "Tony"),
      new Book(2, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài")
    };

    model.addAttribute(BOOKS, bookCollection);
    model.addAttribute(APP_NAME, appName);
    return "book4";
  }
  
  // Trả về books là mảng của đối tượng kiểu Book, thêm ảnh cho từng sách, CSS đẹp
  @GetMapping(value = "/book6")
  public String getBook6(Model model) {
    Book[] bookCollection = { 
      new Book(1, "Cafe cùng Tony", "Tony"),
      new Book(2, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài"),
      new Book(3, "Gone with wind", "Magaret Michel"),
      new Book(4, "Mắt Biếc", "Nguyễn Nhật Ánh"),
      new Book(5, "Không gia đình", "Hector Malot"),
      new Book(6, "Hoàng tử bé", "Saint-Exupéry")
    };

    model.addAttribute(BOOKS, bookCollection);
    model.addAttribute(APP_NAME, appName);
    return "book6";
  }
}