package vn.techmaster.bookstore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.repository.BookDao;

@Controller
@RequestMapping("/book")
public class BookController {
  @Autowired
  private BookDao bookDao;

  @GetMapping
  public String listAll(Model model) {
    model.addAttribute("books", bookDao.getAll());
    return "allbooks";
  }

  @GetMapping(value = "/{id}")
  public String getByID(@PathVariable("id") int id, Model model) {
    Optional<Book> book = bookDao.get(id);
    if (book.isPresent()) {
      model.addAttribute("book", book.get());
    }
    return "book";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("book", new Book()); // Tạo đối tượng Book rỗng để truyền vào th:object="${book}"
    return "form";
  }
  
  @PostMapping("/save")
  public String save(Book book, BindingResult result) {
    if (result.hasErrors()) {
      return "form";
    }
    if (book.getId() > 0) { // Nếu có trường id có nghĩa là đây là edit form
      bookDao.update(book);
    } else { // Nếu id ==0 có nghĩa book lần đầu được add
      bookDao.add(book);
    }

    return "redirect:/book";
  }

  @GetMapping(value = "/edit/{id}")
  public String editBookId(@PathVariable("id") int id, Model model) {
    Optional<Book> book = bookDao.get(id);
    if (book.isPresent()) {
      model.addAttribute("book", book.get());
    }
    return "form";
  }
}