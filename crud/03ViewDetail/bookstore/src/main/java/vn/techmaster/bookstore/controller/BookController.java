package vn.techmaster.bookstore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
