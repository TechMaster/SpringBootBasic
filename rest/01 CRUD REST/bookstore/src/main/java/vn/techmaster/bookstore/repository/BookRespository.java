package vn.techmaster.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.bookstore.model.Book;

public interface BookRespository extends JpaRepository<Book, Long> {
  
}
