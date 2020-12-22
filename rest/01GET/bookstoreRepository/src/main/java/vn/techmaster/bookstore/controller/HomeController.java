package vn.techmaster.bookstore.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.techmaster.bookstore.model.Book;

@Controller
public class HomeController {

  @Autowired
  private 

  @GetMapping("/")
  public String book(Model model) {

   
    model.addAttribute("books", books);
    return "book";
  }
}