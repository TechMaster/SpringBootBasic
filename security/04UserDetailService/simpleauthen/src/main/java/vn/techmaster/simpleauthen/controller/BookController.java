package vn.techmaster.simpleauthen.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.techmaster.simpleauthen.model.Book;
import vn.techmaster.simpleauthen.repository.BookDao;
import vn.techmaster.simpleauthen.request.SearchRequest;

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

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("book", new Book());
    return "form";
  }

  @GetMapping(value = "/{id}")
  public String getByID(@PathVariable("id") int id, Model model) {
    Optional<Book> book = bookDao.get(id);
    if (book.isPresent()) {
      model.addAttribute("book", book.get());
    }    
    return "book";
  }

  @PostMapping("/save")
  public String save(Book book, BindingResult result, RedirectAttributes redirect) {
    if (result.hasErrors()) {
      return "form";
    }
    if (book.getId() > 0) { //Nếu có trường id có nghĩa là đây là edit form
      bookDao.update(book);
    } else { //Nếu id ==0 có nghĩa book lần đầu được add
      bookDao.add(book);
    }
        
    return "redirect:/book";
  }

  @GetMapping(value = "/delete/{id}")
  public String deleteByID(@PathVariable("id") int id) {    
    bookDao.deleteByID(id);        
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

  @GetMapping("/search")
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
}
