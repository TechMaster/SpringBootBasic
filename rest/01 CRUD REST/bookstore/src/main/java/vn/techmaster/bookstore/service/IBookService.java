package vn.techmaster.bookstore.service;

import java.util.List;
import java.util.Optional;

import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.BookPOJO;

public interface IBookService {
  List<Book> findAll();

  Optional<Book> findById(Long id);

  Book save(BookPOJO book);

  void update(long id, BookPOJO book);

  void updateTitle(long id, String title);

  void deleteById(long id);
}
