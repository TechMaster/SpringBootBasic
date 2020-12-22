package vn.techmaster.bookstore.repository;

import java.util.List;

import vn.techmaster.bookstore.model.Book;

public interface IBookRepo {
  List<Book> getAll();
}