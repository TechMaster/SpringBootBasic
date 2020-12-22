package vn.techmaster.bookstore;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String book(Model model) {

    ArrayList<Book> books = new ArrayList<>();

    books.add(new Book(1, "Good with the wind", "gone_with_the_wind.jpg"));
    books.add(new Book(2, "Nhà Giả Kim Thuật", "gia_kim_thuat.jpg"));
    books.add(new Book(3, "Harry Porter", "harry_porter.jpg"));
    books.add(new Book(4, "Deep Work", "deep_work.jpg"));
    model.addAttribute("books", books);
    return "book";
  }
}